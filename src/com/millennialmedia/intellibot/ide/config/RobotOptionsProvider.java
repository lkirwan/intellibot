package com.millennialmedia.intellibot.ide.config;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author mrubino
 * @since 2014-06-26
 */
@State(
        name = "RobotOptionsProvider",
        storages = {
                @Storage(file = StoragePathMacros.WORKSPACE_FILE)
        }
)
public class RobotOptionsProvider implements PersistentStateComponent<RobotOptionsProvider.State> {

    public static class State {
        public boolean transitiveImports = true;
        public boolean globalVariables = true;
        public boolean debug = false;
        public boolean capitalizeKeywords = false;
    }

    private State state = new State();

    @NotNull
    private final Project myProject;

    public RobotOptionsProvider(@NotNull Project project) {
        myProject = project;
    }

    public static RobotOptionsProvider getInstance(Project project) {
        return ServiceManager.getService(project, RobotOptionsProvider.class);
    }

    @Nullable
    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public void loadState(State state) {
        this.state.debug = state.debug;
        this.state.transitiveImports = state.transitiveImports;
        this.state.globalVariables = state.globalVariables;
        this.state.capitalizeKeywords = state.capitalizeKeywords;
    }

    public boolean isDebug() {
        return this.state.debug;
    }

    public void setDebug(boolean debug) {
        this.state.debug = debug;
    }

    public boolean allowTransitiveImports() {
        return this.state.transitiveImports;
    }

    public void setTransitiveImports(boolean transitiveImports) {
        this.state.transitiveImports = transitiveImports;
    }

    public boolean allowGlobalVariables() {
        return this.state.globalVariables;
    }

    public void setGlobalVariables(boolean globalVariables) {
        this.state.globalVariables = globalVariables;
    }

    public boolean capitalizeKeywords() {
        return this.state.capitalizeKeywords;
    }

    public void setCapitalizeKeywords(boolean capitalizeKeywords) {
        this.state.capitalizeKeywords = capitalizeKeywords;
    }
}
