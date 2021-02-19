// Copyright 2011-2021 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

#ifndef CALL_GRAPH_MATCH_FUNCTION_CALL_GRAPH_EDGES_MDINDEX_H_
#define CALL_GRAPH_MATCH_FUNCTION_CALL_GRAPH_EDGES_MDINDEX_H_

#include "third_party/zynamics/bindiff/call_graph_match.h"

namespace security::bindiff {

// Matches callgraph edges based on their callgraph MD index. This means the
// callgraph leading to that particular call is structurally identical in both
// binaries. Match quality depends on how deep the callstack leading up to this
// edge is: the deeper the less likely is a false match.
class MatchingStepEdgesCallGraphMdIndex : public BaseMatchingStepEdgesMdIndex {
 public:
  MatchingStepEdgesCallGraphMdIndex()
      : BaseMatchingStepEdgesMdIndex(
            "function: edges callgraph MD index",
            "Function: Edges Call Graph MD Index",
            MatchingContext::kCallGraphMdIndexPrimary,
            MatchingContext::kCallGraphMdIndexSecondary) {}

 protected:
  EdgeFeature MakeEdgeFeature(CallGraph::Edge edge, const CallGraph& call_graph,
                              FlowGraph* source, FlowGraph* target) override {
    return {edge, call_graph.GetMdIndex(edge), 0.0};
  }
};

}  // namespace security::bindiff

#endif  // CALL_GRAPH_MATCH_FUNCTION_CALL_GRAPH_EDGES_MDINDEX_H_
