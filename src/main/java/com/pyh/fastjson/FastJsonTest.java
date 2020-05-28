package com.pyh.fastjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonTest {

    public static void main(String[] args) {
        User user = new User();
        List<Info> infos = new ArrayList<Info>();
        Info info0 = new Info();
        info0.setAddress("aa0");
        info0.setName("nn0");
        infos.add(info0);
        Info info1 = new Info();
        info1.setAddress("aa1");
        info1.setName("nn1");
        infos.add(info1);
        
        Base base = new Base();
        base.setBase("base1");
        base.setInfos(infos);
        
        Map<Long, Base> infoMap = new HashMap<Long, Base>();
        infoMap.put(1111L, base);
        
        user.setExt("ext1");
        user.setInfos(infoMap);
        
        List<Info> infos2 = new ArrayList<Info>();
        Info info2 = new Info();
        info2.setAddress("aa2");
        info2.setName("nn2");
        infos2.add(info2);
        user.setNormalInfos(infos2);
        
        Info info3 = new Info();
        info3.setAddress("aa3");
        info3.setName("nn3");
        Map<Long, Info> infos3 = new HashMap<Long, Info>();
        infos3.put(333L, info3);
        user.setExInfos(infos3);
        
        Info info4 = new Info();
        info4.setAddress("aa4");
        info4.setName("nn4");
        Set<Info> infos4 = new HashSet<Info>();
        infos4.add(info4);
        user.setSetInfos(infos4);
        
        
        System.out.println(JSON.toJSONString(user, new SerializerFeature[]{SerializerFeature.WriteClassName}));
    }

    static class User {
        private Map<Long, Base> infos;
        private String ext;
        private List<Info> normalInfos;
        private Map<Long, Info> exInfos;
        private Set<Info> setInfos;


        public Map<Long, Base> getInfos() {
            return infos;
        }
        public void setInfos(Map<Long, Base> infos) {
            this.infos = infos;
        }
        public String getExt() {
            return ext;
        }
        public void setExt(String ext) {
            this.ext = ext;
        }
        public List<Info> getNormalInfos() {
            return normalInfos;
        }
        public void setNormalInfos(List<Info> normalInfos) {
            this.normalInfos = normalInfos;
        }
        public Map<Long, Info> getExInfos() {
            return exInfos;
        }
        public void setExInfos(Map<Long, Info> exInfos) {
            this.exInfos = exInfos;
        }
        public Set<Info> getSetInfos() {
            return setInfos;
        }
        public void setSetInfos(Set<Info> setInfos) {
            this.setInfos = setInfos;
        }



    }

    static class Base {
        private String base;
        private List<Info> infos;
        public String getBase() {
            return base;
        }
        public void setBase(String base) {
            this.base = base;
        }
        public List<Info> getInfos() {
            return infos;
        }
        public void setInfos(List<Info> infos) {
            this.infos = infos;
        }

    }

    static class Info {
        private String name;
        private String address;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }

    }
    
}




