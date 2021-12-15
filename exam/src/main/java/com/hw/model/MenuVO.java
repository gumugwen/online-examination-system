package com.hw.model;

import java.util.List;

/**
 * @author  
 * @date 2020/11/19 9:08 上午
 */
public class MenuVO {

    private HomeInfo homeInfo;

    private LogoInfo logoInfo;

    private List<MenuInfo> menuInfo;

    public MenuVO() {
        homeInfo=new HomeInfo();
        logoInfo=new LogoInfo();

    }

    public static class HomeInfo{
        private String title;
        private String href;

        public HomeInfo() {
            title="首页";
            href="test/toStudentIndex";
            //href="page/welcome-1.html?t=1";
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        @Override
        public String toString() {
            return "HomeInfo{" +
                    "title='" + title + '\'' +
                    ", href='" + href + '\'' +
                    '}';
        }
    }
    private class LogoInfo{
        private String title="LAYUI MINI";
        private String image="images/logo.png";
        private String href;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHref() {
            return "";
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public static class  MenuInfo{
        private String title;
        private String icon;
        private String href;
        private String target;
        private List<MenuInfo> child;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

       /* public List<Menu> getChild() {
            return child;
        }

        public void setChild(List<Menu> child) {
            this.child = child;
        }*/

        public List<MenuInfo> getChild() {
            return child;
        }

        public void setChild(List<MenuInfo> child) {
            this.child = child;
        }

        @Override
        public String toString() {
            return "MenuInfo{" +
                    "title='" + title + '\'' +
                    ", icon='" + icon + '\'' +
                    ", href='" + href + '\'' +
                    ", target='" + target + '\'' +
                    ", child=" + child +
                    '}';
        }
    }

    public HomeInfo getHomeInfo() {
        return homeInfo;
    }

    public void setHomeInfo(HomeInfo homeInfo) {
        this.homeInfo = homeInfo;
    }

    public LogoInfo getLogoInfo() {
        return logoInfo;
    }

    public void setLogoInfo(LogoInfo logoInfo) {
        this.logoInfo = logoInfo;
    }

    public List<MenuInfo> getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(List<MenuInfo> menuInfo) {
        this.menuInfo = menuInfo;
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "homeInfo=" + homeInfo +
                ", logoInfo=" + logoInfo +
                ", menuInfo=" + menuInfo +
                '}';
    }
}
