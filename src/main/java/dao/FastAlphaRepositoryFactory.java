package dao;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public class FastAlphaRepositoryFactory implements AlphaRepositoryFactory {
    private Class<? extends AlphaRepository> repositoryClass=SimpleAlphaRepository.class;

    private static final FastAlphaRepositoryFactory fastAlphaRepositoryFactory=new FastAlphaRepositoryFactory();

    private static long increasingId=0;

    private FastAlphaRepositoryFactory(){ }

    public static FastAlphaRepositoryFactory getInstance(){
        return fastAlphaRepositoryFactory;
    }


    private long nextId(){
        return ++increasingId;
    }

    @Override
    public AlphaRepository getEmptyAlphaRepository() {
      return getEmptyAlphaRepository(this.repositoryClass);
    }

    @Override
    public AlphaRepository getEmptyAlphaRepository(Class<? extends AlphaRepository> repositoryClass) {
        try{
            AlphaRepository alphaRepository=repositoryClass.newInstance();
            alphaRepository.setId(nextId());
            return alphaRepository;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setDefaultAlphaRepositoryClass(Class<? extends AlphaRepository> repositoryClass) {
        this.repositoryClass=repositoryClass;
    }

    public Class<? extends AlphaRepository> getDefaultAlphaRepositoryClass() {
        return repositoryClass;
    }
}
