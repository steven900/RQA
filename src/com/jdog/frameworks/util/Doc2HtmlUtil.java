//package com.jdog.frameworks.util;
//
//public class Doc2HtmlUtil {
//	
//		 
//	    public static void main(String[] args) throws Exception {
//	        ReleaseManager rm = new ReleaseManager();
//	        try {
//	            System.out.println("Word傪婲摦拞...");
//	            IDispatch wdApp = new IDispatch(rm, "Word.Application");  // EXCEL杮懱
//	            wdApp.put("Visible", new Boolean(true));    // '僨僼僅儖僩偼False(昞帵偟側偄)
//	 
//	            IDispatch wdDocuments = (IDispatch) wdApp.get("Documents");
//	            Object[] arglist1 = new Object[1];
//	            arglist1[0] = "。。。。/JCom.doc";//文件目录
//	            System.out.println(arglist1[0]);
//	            IDispatch wdDocument = (IDispatch) wdDocuments.method("Open", arglist1);
//	            String fullname = (String) wdDocument.get("FullName");
//	            System.out.println("fullname=" + fullname);
//	 
//	            IDispatch wdWords = (IDispatch) wdDocument.get("Words");
//	            int word_count = ((Integer) wdWords.get("Count")).intValue();
//	            for (int i = 0; i < word_count; i++) {
//	                Object[] index = new Object[1];
//	                index[0] = new Integer(i + 1);       
//	                IDispatch wdWord = (IDispatch) wdWords.method("Item", index);
//	                String text = (String) wdWord.get("Text");
//	                System.out.println(text);
//	            }
//	 
//	            IDispatch wdTables = (IDispatch) wdDocument.get("Tables");
//	            System.out.println(wdTables);
//	            int table_count = ((Integer) wdTables.get("Count")).intValue();
//	            System.out.println("昞偺悢=" + table_count);
//	            for (int i = 0; i < table_count; i++) {
//	                Object[] index = new Object[1];
//	                index[0] = new Integer(i + 1);       
//	                IDispatch wdTable = (IDispatch) wdTables.method("Item", index);
//	                System.out.println("" + i + "=" + wdTable);
//	            }
//	 
//	            //wdDocument.method("PrintOut", null);  // 摦嶌枹妋擣
//	 
//	            System.out.println("俁昩屻偵廔椆偟傑偡丅");
//	            Thread.sleep(3000); // 3sec
//	            wdApp.method("Quit", null);
//	            System.out.println("偛惷挳丄偁傝偑偲偆偛偞偄傑偟偨丅");
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        } finally {
//	            rm.release();
//	        }
//	    }
//	}
//	
//}
