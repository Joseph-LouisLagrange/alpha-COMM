package dao;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public interface AlphaRepositoryFactory {
    public AlphaRepository getEmptyAlphaRepository();

    public AlphaRepository getEmptyAlphaRepository(Class<? extends AlphaRepository> repositoryClass);

    public void setDefaultAlphaRepositoryClass(Class<? extends AlphaRepository> repositoryClass);

    public Class<? extends AlphaRepository> getDefaultAlphaRepositoryClass();
}
