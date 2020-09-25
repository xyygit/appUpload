package com.tilive.appupdate.dao;

import com.tilive.appupdate.bean.BaseBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonDao<T extends BaseBean> extends JpaRepository<T, Long> {
}
