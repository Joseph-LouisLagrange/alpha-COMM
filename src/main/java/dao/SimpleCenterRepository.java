package dao;

import dto.endpoint.Endpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public class SimpleCenterRepository implements CenterRepository {

    private List<AlphaRepository> alphaRepositories=new ArrayList<>();

    private static final SimpleCenterRepository simpleCenterRepository = new SimpleCenterRepository();

    private SimpleCenterRepository(){}


    public static SimpleCenterRepository getInstance(){
        return simpleCenterRepository;
    }

    @Override
    public List<AlphaRepository> getAllAlphaRepository() {
        return this.alphaRepositories;
    }

    @Override
    public List<AlphaRepository> getAlphaRepositoryByCategory(AlphaRepository.Category category) {
        return null;
    }

    @Override
    public List<AlphaRepository> getAlphaRepositoryOfPrivate() {
        return null;
    }

    @Override
    public AlphaRepository getAlphaRepositoryByUser(Endpoint user) {
        return null;
    }

    @Override
    public void addAlphaRepository(AlphaRepository alphaRepository) {
        this.alphaRepositories.add(alphaRepository);
    }


    @Override
    public void removeAlphaRepositoryOfPrivate(Endpoint user) {

    }
}
