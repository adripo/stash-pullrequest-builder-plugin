package stashpullrequestbuilder.stashpullrequestbuilder.stash;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StashPullRequestResponseValueRepositoryRepository {
  private String slug;
  private StashPullRequestResponseValueRepositoryProject project;

  @JsonProperty("project")
  public StashPullRequestResponseValueRepositoryProject getRepository() {
    return project;
  }

  @JsonProperty("project")
  public void setRepository(StashPullRequestResponseValueRepositoryProject project) {
    this.project = project;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getProjectName() {
    if (this.project != null && project.getKey() != null) {
      return project.getKey();
    }
    return null;
  }

  public String getRepositoryName() {
    return this.slug;
  }
}
