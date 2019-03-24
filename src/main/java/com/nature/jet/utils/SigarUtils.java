package com.nature.jet.utils;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务器系统信息工具类
 * SigarUtils
 *
 * @Author: 竺志伟
 * @Date: 2019-03-19 16:34
 */
public class SigarUtils
{

    /**
     * 内存信息
     * Gets mem.
     *
     * @return the mem
     * @author:竺志伟
     * @date :2019-03-19 16:40:04
     */
    public static Map<String, Long> getMem(Sigar sigar)
    {
        Map<String, Long> map = new HashMap<>();
        try
        {
            Mem mem = sigar.getMem();
            map.put("total", mem.getTotal() / 1024 / 1024);
            map.put("use", mem.getUsed() / 1024 / 1024);
            map.put("free", mem.getFree() / 1024 / 1024);
        }
        catch(SigarException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return map;
        }
    }


    /**
     * cpu信息
     * Gets cpu.
     *
     * @param sigar the sigar
     * @return the cpu
     * @author:竺志伟
     * @date :2019-03-19 18:54:35
     */
    public static List<Map<String, Double>> getCpu(Sigar sigar)
    {
        List<Map<String, Double>> list = new ArrayList<>();
        try
        {
            Map<String, Double> map = null;
            CpuPerc[] cpus = sigar.getCpuPercList();
            DecimalFormat df = new DecimalFormat("#.00");
            for(CpuPerc info : cpus)
            {
                map = new HashMap<>();
                map.put("user", Double.valueOf(df.format(info.getUser() * 100))); //用户使用率
                map.put("sys", Double.valueOf(df.format(info.getSys() * 100))); // 系统使用率
                map.put("idle", Double.valueOf(df.format(info.getIdle() * 100))); //当前空闲
                map.put("total", Double.valueOf(df.format(info.getCombined() * 100))); //总使用率
                list.add(map);
            }
        }
        catch(SigarException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return list;
        }
    }

}
