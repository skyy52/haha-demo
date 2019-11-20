package com.qfjy.project.weixin.bean.resp;

/**
 * 音乐消息
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private com.qfjy.project.weixin.bean.resp.Music Music;

	public com.qfjy.project.weixin.bean.resp.Music getMusic() {
		return Music;
	}

	public void setMusic(com.qfjy.project.weixin.bean.resp.Music music) {
		Music = music;
	}
}
