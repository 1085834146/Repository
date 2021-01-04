package com.fjndljh.repository;


import com.fjndljh.entity.Reply;

import java.util.List;

public interface ReplyRepository {
    public int save(Reply reply);
    public List<Reply> findByBlogID(long id);
    public int deleteReply(long id);
    public int deleteReplyByBlogID(long id);
}

