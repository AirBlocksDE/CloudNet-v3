/*
 * Copyright 2019-2021 CloudNetService team & contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.dytanic.cloudnet.console.animation.questionlist;

import de.dytanic.cloudnet.common.language.LanguageManager;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface QuestionAnswerType<T> {

  boolean isValidInput(@NotNull String input);

  @Nullable T parse(@NotNull String input);

  /**
   * @return null if there are infinite possible numbers
   */
  @Nullable Collection<String> getPossibleAnswers();

  default @Nullable List<String> getCompletableAnswers() {
    return null;
  }

  default @Nullable String getRecommendation() {
    return null;
  }

  default @Nullable String getPossibleAnswersAsString() {
    Collection<String> possibleAnswers = this.getPossibleAnswers();
    return possibleAnswers != null ? String.join(", ", possibleAnswers) : null;
  }

  default @Nullable String getInvalidInputMessage(@NotNull String input) {
    String s = this.getPossibleAnswersAsString();
    if (s != null) {
      return LanguageManager.getMessage("ca-question-list-question-list").replace("%values%", s);
    }

    return LanguageManager.getMessage("ca-question-list-invalid-default");
  }
}
