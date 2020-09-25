package dao;

import dto.endpoint.Endpoint;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/9/19
 */
public interface CenterRepository {
    public List<AlphaRepository> getAllAlphaRepository();

    public List<AlphaRepository> getAlphaRepositoryByCategory(AlphaRepository.Category category);

    public List<AlphaRepository> getAlphaRepositoryOfPrivate();

    public AlphaRepository getAlphaRepositoryByUser(Endpoint user);

    public void addAlphaRepository(AlphaRepository alphaRepository);

    public void removeAlphaRepositoryOfPrivate(Endpoint user);

}
