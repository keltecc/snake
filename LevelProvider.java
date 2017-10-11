package snake;

public interface LevelProvider 
{
    public int getLevelsCount() throws Exception;
    public Level load(int number) throws Exception;
}
