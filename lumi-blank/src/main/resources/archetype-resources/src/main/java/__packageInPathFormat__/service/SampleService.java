package ${package}.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import lumi.dao.DAO;
import lumi.service.LumiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Serviceクラスのサンプル。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Scope("prototype")
@Service
@Slf4j
public class SampleService extends LumiService {

	/**
	 * サンプルのServiceクラス。
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> execute(String param) throws Exception {

		// 検索パラメータの作成
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyname", param);

		// 検索結果
		List<Map<String,Object>> resultList =
				dao.select(Query.sampleList.name(), param);

		// 検索結果を返す。
		return resultList;
	}

	/**
	 * DAOの指定。Mybatisを利用してデータベースアクセスを実行する。
	 */
	@Autowired
	private DAO dao;

	/**
	 * Mybatisで定義するSQLのSQL-ID。
	 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
	 *
	 */
	public enum Query {
		sampleList ,
	}
}
