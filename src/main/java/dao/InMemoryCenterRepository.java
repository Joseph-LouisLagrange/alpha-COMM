package dao;

import dao.repository.AlphaRepository;
import dto.Alpha;
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
    public void registerUser(Endpoint user) {

    }

    @Override
    public void addAlphaRepositoryOfPrivate(Endpoint user1, Endpoint user2) {

    }

    @Override
    public void addAlphaRepositoryOfPublic(Endpoint host) {

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
    public AlphaRepository getAlphaRepositoryOfPrivateByUser(Endpoint user1, Endpoint user2) {
        return null;
    }

    @Override
    public void addAlphaRepository(AlphaRepository alphaRepository) {
        this.alphaRepositories.add(alphaRepository);
    }

    @Override
    public void removeAlphaRepositoryOfPrivate(Endpoint user1, Endpoint user2) {

    }

    @Override
    public void removeAlphaRepositoryOfPublic(long id) {

    }

    @Override
    public List<Alpha> getAllUnread(Endpoint endpoint) {
        return null;
    }

    @Override
    public Alpha popUnreadAlpha(Endpoint endpoint, long id) {
        return null;
    }

    @Override
    public void addUnread(Endpoint endpoint, Alpha alpha) {

    }
}
