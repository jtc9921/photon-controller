/*
 * Copyright 2015 VMware, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.vmware.photon.controller.api.backend.builders;

import com.vmware.photon.controller.api.model.Operation;
import com.vmware.photon.controller.cloudstore.xenon.entity.TaskService;

import java.util.ArrayList;
import java.util.List;

/**
 * This implements a builder for {@link com.vmware.photon.controller.cloudstore.xenon.entity.TaskService.State} object.
 */
public class TaskStateBuilder {

  private String entityId;

  private String entityKind;

  private Operation operation;

  private TaskService.State.TaskState taskState;

  private List<TaskService.State.Step> stepEntities;

  public TaskStateBuilder() {
    stepEntities = new ArrayList<>();
  }

  public TaskStateBuilder setEntityId(String entityId) {
    this.entityId = entityId;
    return this;
  }

  public TaskStateBuilder setEntityKind(String entityKind) {
    this.entityKind = entityKind;
    return this;
  }

  public TaskStateBuilder setOperation(Operation operation) {
    this.operation = operation;
    return this;
  }

  public TaskStateBuilder setState(TaskService.State.TaskState taskState) {
    this.taskState = taskState;
    return this;
  }

  public TaskStateBuilder addStep(Operation operation) {
    TaskService.State.Step step = new TaskService.State.Step();
    step.sequence = stepEntities.size();
    step.operation = operation.getOperation();
    step.state = TaskService.State.StepState.QUEUED;
    stepEntities.add(step);
    return this;
  }

  public TaskService.State build() {
    TaskService.State state = new TaskService.State();
    state.entityId = this.entityId;
    state.entityKind = this.entityKind;
    state.operation = this.operation.getOperation();
    state.state = this.taskState != null ? this.taskState : TaskService.State.TaskState.QUEUED;
    state.steps = this.stepEntities;
    return state;
  }
}
