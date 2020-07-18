package Model;

public class SaveAble {

    String nameinsave;

    protected String getName() {
        return nameinsave;
    }

    public SaveAble() { }

    SaveAndLoad saveAndLoad = SaveAndLoad.getSaveAndLoad();
    //public abstract String getPassWord();

}
