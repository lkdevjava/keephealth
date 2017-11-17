package com.kh.common.shiro.session.service;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

import com.kh.common.exception.CacheException;

public interface ShiroSessionService {
    
    public boolean saveSession(Session session) throws CacheException;

    public boolean deleteSession(Session session) throws CacheException;

    public void updateSession(Session session) throws CacheException;

    public Session getSession(Serializable id) throws CacheException;

    public Collection<Session> getSessions() throws CacheException;

    public int size() throws CacheException;

}
