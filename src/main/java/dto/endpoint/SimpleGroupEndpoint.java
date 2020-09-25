package dto.endpoint;

/**
 * 简单的组端
 */
public class SimpleGroupEndpoint extends Endpoint {

    private long groupId = 0;

    /**
     * @param groupId 群组的id
     */
    public SimpleGroupEndpoint(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getTypeKey() {
        return this.getClass().getSimpleName();
    }
}
