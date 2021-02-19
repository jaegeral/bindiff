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

package com.google.security.zynamics.bindiff.gui.tabpanels.viewtabpanel.graphnodetree.comparators;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.security.zynamics.bindiff.enums.EMatchState;
import com.google.security.zynamics.bindiff.enums.EMatchType;
import com.google.security.zynamics.bindiff.enums.ESortOrder;
import com.google.security.zynamics.bindiff.gui.tabpanels.viewtabpanel.graphnodetree.sorter.ISortableTreeNode;
import java.util.Comparator;

public class BasicBlockTreeNodeMatchStateComparator implements Comparator<ISortableTreeNode> {
  private final ESortOrder sortOrder;

  public BasicBlockTreeNodeMatchStateComparator(final ESortOrder order) {
    this.sortOrder = checkNotNull(order);
  }

  @Override
  public int compare(final ISortableTreeNode o1, final ISortableTreeNode o2) {
    int value = 0;

    if (o1.getMatchState() == EMatchState.MATCHED && o2.getMatchState() == EMatchState.MATCHED) {
      if (o1.getMatchType() == o2.getMatchType()) {
        return 0;
      } else if (o1.getMatchType() == EMatchType.IDENTICAL) {
        value = -1;
      } else if (o1.getMatchType() == EMatchType.INSTRUCTIONS_CHANGED) {
        value = 1;
      }
    } else if (o1.getMatchState() != EMatchState.MATCHED
        && o2.getMatchState() == EMatchState.MATCHED) {
      value = 1;
    } else if (o1.getMatchState() == EMatchState.MATCHED
        && o2.getMatchState() != EMatchState.MATCHED) {
      value = -1;
    }

    if (sortOrder == ESortOrder.DESCENDING) {
      value *= -1;
    }

    return value;
  }
}
