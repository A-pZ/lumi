package ${package}.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.extern.slf4j.Slf4j;
import lumi.dao.DAO;
import lumi.service.LumiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * アプリケーション起動時に１つだけ立ち上がるServiceクラスのサンプル。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Scope("singleton")
@Service
@Slf4j
public class SampleInitialService extends LumiService {

	/**
	 * 起動時の処理。
	 * @throws Exception
	 */
	@PostConstruct
	public void start() throws Exception {
		log.info(" - start.");
	}

	/**
	 * 終了時の処理。
	 * @throws Exception
	 */
	@PreDestroy
	public void destroy() throws Exception {
		log.info(" - destroy.");
	}

	/**
	 * DAOの指定。Mybatisを利用してデータベースアクセスを実行する。
	 */
	@Autowired
	private DAO dao;
}
