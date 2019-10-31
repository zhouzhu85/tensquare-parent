package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-10-30 17:02
 */
public interface FriendDao extends JpaRepository<Friend,String> {

    public Friend findByUseridAndFriendid(String userid,String friendid);

    @Modifying
    @Query(value = "UPDATE tb_friend SET islike=? WHERE userid=? AND friendid=?",nativeQuery = true)
    public void updateIslike(String islike,String userid,String friendid);

    @Modifying
    @Query(value = "delete FROM tb_friend where userid=? and friendid=?",nativeQuery = true)
    void deleteFriend(String userId, String friendid);
}
