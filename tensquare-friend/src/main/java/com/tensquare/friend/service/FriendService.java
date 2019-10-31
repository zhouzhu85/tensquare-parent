package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-10-30 16:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userId, String friendid) {
        Friend friend = friendDao.findByUseridAndFriendid(userId, friendid);
        if (friend !=null){
            return 0;
        }
        //直接添加好友，好友表中userid到friendid方向的type为0
        friend=new Friend();
        friend.setUserid(userId);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断从friendid到userid是否有数据，如果有，把双方的状态都改为1
        if (friendDao.findByUseridAndFriendid(friendid,userId)!=null){
            friendDao.updateIslike("1",userId,friendid);
            friendDao.updateIslike("1",friendid,userId);
        }
        return 1;
    }

    public int addNoFriend(String userId, String friendid) {
        //判断是否已经是非好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userId, friendid);
        if (noFriend!=null){
            return 0;
        }
        noFriend=new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userId, String friendid) {
        //删除好友表中userid到friendid这条数据
        friendDao.deleteFriend(userId,friendid);
        //更新friendid到userid的islike为0
        friendDao.updateIslike("0",friendid,userId);
        //非好友表中添加数据
        NoFriend noFriend=new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}
