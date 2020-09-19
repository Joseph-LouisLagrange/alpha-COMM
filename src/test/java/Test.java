import dao.AlphaRepository;
import dao.FastAlphaRepositoryFactory;
import dao.SimpleCenterRepository;

/**
 * @author 杨能
 * @create 2020/9/18
 */
public class Test {
    public static void main(String[] args) {
        FastAlphaRepositoryFactory fastAlphaRepositoryFactory=FastAlphaRepositoryFactory.getInstance();
        AlphaRepository alphaRepository=fastAlphaRepositoryFactory.getEmptyAlphaRepository();
        alphaRepository.setPrivate(null);
        SimpleCenterRepository centerRepository=SimpleCenterRepository.getInstance();
        centerRepository.addAlphaRepository(alphaRepository);
        System.out.println(centerRepository.getAllAlphaRepository().get(0).toString());
    }
}
