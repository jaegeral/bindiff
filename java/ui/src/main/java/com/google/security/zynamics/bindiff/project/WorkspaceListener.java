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

package com.google.security.zynamics.bindiff.project;

import com.google.security.zynamics.bindiff.project.diff.Diff;

/** Listener interface for workspace events. */
public interface WorkspaceListener {

  default void addedDiff(final Diff diff) {
    // Do nothing by default
  }

  default void closedWorkspace() {
    // Do nothing by default
  }

  default void loadedWorkspace(final Workspace workspace) {
    // Do nothing by default
  }

  default void removedDiff(final Diff diff) {
    // Do nothing by default
  }
}
