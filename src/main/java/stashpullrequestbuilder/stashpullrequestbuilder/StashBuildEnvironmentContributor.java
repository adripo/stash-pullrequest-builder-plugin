package stashpullrequestbuilder.stashpullrequestbuilder;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.EnvironmentContributor;
import hudson.model.Run;
import hudson.model.TaskListener;
import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nonnull;

@Extension
public class StashBuildEnvironmentContributor extends EnvironmentContributor {
  @Override
  public void buildEnvironmentFor(
      @Nonnull Run r, @Nonnull EnvVars envs, @Nonnull TaskListener listener)
      throws IOException, InterruptedException {
    if (r instanceof AbstractBuild) {
      AbstractBuild<?, ?> build = (AbstractBuild<?, ?>) r;
      AbstractBuild<?, ?> rootBuild = build.getRootBuild();

      StashCause cause = rootBuild.getCause(StashCause.class);
      if (cause != null) {
        putEnvVar(envs, "sourceBranch", cause.getSourceBranch());
        putEnvVar(envs, "targetBranch", cause.getTargetBranch());
        putEnvVar(envs, "sourceRepositoryOwner", cause.getSourceRepositoryOwner());
        putEnvVar(envs, "sourceRepositoryName", cause.getSourceRepositoryName());
        putEnvVar(envs, "pullRequestId", cause.getPullRequestId());
        putEnvVar(envs, "destinationRepositoryOwner", cause.getDestinationRepositoryOwner());
        putEnvVar(envs, "destinationRepositoryName", cause.getDestinationRepositoryName());
        putEnvVar(envs, "pullRequestTitle", cause.getPullRequestTitle());
        putEnvVar(envs, "sourceCommitHash", cause.getSourceCommitHash());
        putEnvVar(envs, "destinationCommitHash", cause.getDestinationCommitHash());
      }
    }

    super.buildEnvironmentFor(r, envs, listener);
  }

  private static void putEnvVar(EnvVars envs, String key, String value) {
    envs.put(key, Objects.toString(value, ""));
  }
}
