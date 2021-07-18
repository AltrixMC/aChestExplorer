package fr.altrix.achestexplorer;

public interface ILanguages {

    String getLanguage();

    //Give command
    String getGiveCommandParameters();

    String getGiveCommandUtility();

    String getPlayerNotOnlineMessage();

    String getIdNotRecognizedMessage();

    String getPleaseEnterAnEntireNumberMessage();

    String getGiveMessage();

    //Reload command
    String getReloadMessage();

    String getReloadCommandUtility();
}
