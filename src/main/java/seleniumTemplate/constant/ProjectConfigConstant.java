package seleniumTemplate.constant;


public enum ProjectConfigConstant {

    COMMAND_LINE_ENV_NAME("testEnv");

    private final String constantIdentifier;

    ProjectConfigConstant(String constantIdentifier) {
        this.constantIdentifier = constantIdentifier;
    }

    public String getValue() {
        return constantIdentifier;
    }


}
