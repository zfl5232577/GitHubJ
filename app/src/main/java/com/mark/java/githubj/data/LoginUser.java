package com.mark.java.githubj.data;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

import cn.aorise.common.core.module.network.Response;
import cn.aorise.common.core.util.TimeUtils;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/05
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class LoginUser extends Response{

    /**
     * login : zfl5232577
     * id : 13445425
     * node_id : MDQ6VXNlcjEzNDQ1NDI1
     * avatar_url : https://avatars0.githubusercontent.com/u/13445425?v=4
     * gravatar_id :
     * url : https://api.github.com/users/zfl5232577
     * html_url : https://github.com/zfl5232577
     * followers_url : https://api.github.com/users/zfl5232577/followers
     * following_url : https://api.github.com/users/zfl5232577/following{/other_user}
     * gists_url : https://api.github.com/users/zfl5232577/gists{/gist_id}
     * starred_url : https://api.github.com/users/zfl5232577/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/zfl5232577/subscriptions
     * organizations_url : https://api.github.com/users/zfl5232577/orgs
     * repos_url : https://api.github.com/users/zfl5232577/repos
     * events_url : https://api.github.com/users/zfl5232577/events{/privacy}
     * received_events_url : https://api.github.com/users/zfl5232577/received_events
     * type : User
     * site_admin : false
     * name : Makun.cai
     * company : null
     * blog :
     * location : null
     * email : zfl5232577@126.com
     * hireable : null
     * bio : 自律与勤奋
     * public_repos : 23
     * public_gists : 0
     * followers : 2
     * following : 3
     * created_at : 2015-07-22T03:49:41Z
     * updated_at : 2018-12-04T13:54:12Z
     * private_gists : 0
     * total_private_repos : 0
     * owned_private_repos : 0
     * disk_usage : 22098
     * collaborators : 0
     * two_factor_authentication : false
     * plan : {"name":"free","space":976562499,"collaborators":0,"private_repos":0}
     */

    private String login;
    private int id;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private boolean site_admin;
    private String name;
    private Object company;
    private String blog;
    private Object location;
    private String email;
    private Object hireable;
    private String bio;
    private int public_repos;
    private int public_gists;
    private int followers;
    private int following;
    private String created_at;
    private String updated_at;
    private int private_gists;
    private int total_private_repos;
    private int owned_private_repos;
    private int disk_usage;
    private int collaborators;
    private boolean two_factor_authentication;
    private PlanBean plan;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getHireable() {
        return hireable;
    }

    public void setHireable(Object hireable) {
        this.hireable = hireable;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getPublic_gists() {
        return public_gists;
    }

    public void setPublic_gists(int public_gists) {
        this.public_gists = public_gists;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getPrivate_gists() {
        return private_gists;
    }

    public void setPrivate_gists(int private_gists) {
        this.private_gists = private_gists;
    }

    public int getTotal_private_repos() {
        return total_private_repos;
    }

    public void setTotal_private_repos(int total_private_repos) {
        this.total_private_repos = total_private_repos;
    }

    public int getOwned_private_repos() {
        return owned_private_repos;
    }

    public void setOwned_private_repos(int owned_private_repos) {
        this.owned_private_repos = owned_private_repos;
    }

    public int getDisk_usage() {
        return disk_usage;
    }

    public void setDisk_usage(int disk_usage) {
        this.disk_usage = disk_usage;
    }

    public int getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(int collaborators) {
        this.collaborators = collaborators;
    }

    public boolean isTwo_factor_authentication() {
        return two_factor_authentication;
    }

    public void setTwo_factor_authentication(boolean two_factor_authentication) {
        this.two_factor_authentication = two_factor_authentication;
    }

    public PlanBean getPlan() {
        return plan;
    }

    public void setPlan(PlanBean plan) {
        this.plan = plan;
    }


    @SuppressLint("SimpleDateFormat")
    public String transCreateData(){
        return "Joined at " + TimeUtils.getFriendlyTimeSpanByNow(created_at,new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }

    public static class PlanBean {
        /**
         * name : free
         * space : 976562499
         * collaborators : 0
         * private_repos : 0
         */

        private String name;
        private int space;
        private int collaborators;
        private int private_repos;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSpace() {
            return space;
        }

        public void setSpace(int space) {
            this.space = space;
        }

        public int getCollaborators() {
            return collaborators;
        }

        public void setCollaborators(int collaborators) {
            this.collaborators = collaborators;
        }

        public int getPrivate_repos() {
            return private_repos;
        }

        public void setPrivate_repos(int private_repos) {
            this.private_repos = private_repos;
        }
    }
}
