package dao;

import dao.repository.AlphaRepository;
import dto.Alpha;
import dto.endpoint.Endpoint;

import java.util.List;
import java.util.Set;

/**
 * @author 杨能
 * @create 2020/9/19
 * 中央存储库(可拓展为数据库类型)
 */
public interface CenterRepository {

    public void registerUser(Endpoint user);

    public void addAlphaRepositoryOfPrivate(Endpoint user1,Endpoint user2);

    public void addAlphaRepositoryOfPublic(Endpoint host);

    public List<AlphaRepository> getAllAlphaRepository();

    public List<AlphaRepository> getAlphaRepositoryByCategory(AlphaRepository.Category category);

    public List<AlphaRepository> getAlphaRepositoryOfPrivate();

    public List<AlphaRepository> getAlphaRepositoryOfPublicByUser(Endpoint user);

    public List<AlphaRepository> getAlphaRepositoryOfPublicByUserIn(Endpoint user);

    public AlphaRepository getAlphaRepositoryById(long id);

    public AlphaRepository getAlphaRepositoryOfPrivateByUser(Endpoint user1,Endpoint user2);

    public void addAlphaRepository(AlphaRepository alphaRepository);

    public void removeAlphaRepositoryOfPrivate(Endpoint user1,Endpoint user2);

    public void removeAlphaRepositoryOfPublic(long id);

    public List<Alpha> getAllUnread(Endpoint endpoint);

    public Alpha popUnreadAlpha(Endpoint endpoint , long id);

    public void addUnread(Endpoint endpoint , Alpha alpha);

}
