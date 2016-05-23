package wx.weixin.util;

import org.springframework.stereotype.Service;

@Service
public class GentableUtil {
	
//	static{
//		String sql = "SELECT count(0) FROM information_schema.columns WHERE  table_name = 'weixinfuwumenu'";
//		int num = DB.queryForInt(sql);
//		
//		if(num == 0){
//			try {
//			
//			String sqlCreate2="CREATE TABLE `weixinfuwumenu` (`id` int(12) NOT NULL AUTO_INCREMENT,`name` varchar(200) DEFAULT NULL,`wtype` varchar(200) DEFAULT NULL," +
//						" `url` varchar(200) DEFAULT NULL,`dorder` int(12) DEFAULT NULL,`pid` int(12) DEFAULT NULL," +
//						" `available` int(12) DEFAULT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8";
//				
//				JdbcTemplate jt = SpringBeanFactory.getJdbcTemplate();
//				jt.execute(sqlCreate2);
//				System.out.println("--database create success--");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		
//		}else{
//			System.out.println("--no database update--");
//		}
//		
//	}

}
