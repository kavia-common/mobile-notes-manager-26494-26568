#!/bin/bash
cd /home/kavia/workspace/code-generation/mobile-notes-manager-26494-26568/notes_android_app
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

