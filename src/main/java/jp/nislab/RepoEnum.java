package jp.nislab;

public enum RepoEnum {
    closure("closure-compiler.git"), lang("commons-lang.git"), math("commons-math.git"), chart("jfreechart"),
    time("joda-time.git"), mockito("mockito.git"),;

    public String repoID;

    private RepoEnum(String repoID) {
        this.repoID = repoID;
    }
};