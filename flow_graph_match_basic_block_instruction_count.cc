#include "third_party/zynamics/bindiff/flow_graph_match_basic_block_instruction_count.h"

#include "third_party/zynamics/bindiff/flow_graph_match.h"

namespace security::bindiff {

bool MatchingStepInstructionCount ::FindFixedPoints(
    FlowGraph* primary, FlowGraph* secondary, const VertexSet& vertices1,
    const VertexSet& vertices2, FixedPoint* fixed_point,
    MatchingContext* context, MatchingStepsFlowGraph* matching_steps){
  VertexIntMap vertex_map_1;
  VertexIntMap vertex_map_2;
  GetUnmatchedBasicBlocksByInstructionCount(primary, vertices1, &vertex_map_1);
  GetUnmatchedBasicBlocksByInstructionCount(secondary, vertices2,
                                            &vertex_map_2);
  return FindFixedPointsBasicBlockInternal(primary, secondary, &vertex_map_1,
                                           &vertex_map_2, fixed_point, context,
                                           matching_steps);
}

void MatchingStepInstructionCount::GetUnmatchedBasicBlocksByInstructionCount(
    const FlowGraph* flow_graph, const VertexSet& vertices,
    VertexIntMap* basic_blocks_map) {
  basic_blocks_map->clear();
  for (auto vertex : vertices) {
    if (!flow_graph->GetFixedPoint(vertex)) {
      const auto int_md_index = static_cast<uint64_t>(
          flow_graph->GetMdIndex(vertex) * 1000000000000000000ULL);
      basic_blocks_map->emplace(
          int_md_index + flow_graph->GetInstructionCount(vertex), vertex);
    }
  }
}

}  // namespace security::bindiff