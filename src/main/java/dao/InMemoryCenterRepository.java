package dao;

import dto.endpoint.Endpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public class InMemoryCenterRepository implements CenterRepository {

    private List<AlphaRepository> alphaRepositories = new ArrayList<>();

    private static final InMemoryCenterRepository IN_MEMORY_CENTER_REPOSITORY = new InMemoryCenterRepository();

    private InMemoryCenterRepository() {
    }


    public static InMemoryCenterRepository getInstance() {
        return IN_MEMORY_CENTER_REPOSITORY;
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
    public List<AlphaRepository> getAlphaRepositoryOfPublicByUser(Endpoint user) {
        return null;
    }

    @Override
    public List<AlphaRepository> getAlphaRepositoryOfPublicByUserIn(Endpoint user) {
        return null;
    }

    @Override
    public AlphaRepository getAlphaRepositoryById(long id) {
        return null;
    }

    @Override
    public AlphaRepository getAlphaRepositoryOfPrivateByUser(Endpoint user) {
        return null;
    }

    @Override
    public void addAlphaRepository(AlphaRepository alphaRepository) {
        this.alphaRepositories.add(alphaRepository);
    }

    @Override
    public void removeAlphaRepositoryOfPrivate(Endpoint user) {

    }

    @Override
    public void removeAlphaRepositoryOfPublic(long id) {

    }
}
