package com.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.service.PayService;
import com.vo.Bill;

import java.io.*;

/**
 * @Author:fjc
 * @Description:
 * @Date: 2018/7/9
 **/
@Service
public class PayServiceImpl implements PayService {

    public boolean pay(Bill bill){
        System.out.println("bill provider");

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;

        String temp = "";
        try{
            File file = new File("D://data//dubbo//dubbo.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            String filein = getFilein(bill);
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (pw != null) {
                    pw.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    private String getFilein(Bill bill) {
        String fileIn = "order_no="+bill.getOrderNo() + " type=" + bill.getType() + " amount=" + bill.getAmount() + "\r\n";
        return fileIn;
    }
}
