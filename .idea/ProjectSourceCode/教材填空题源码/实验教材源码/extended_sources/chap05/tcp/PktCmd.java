package net.tcp;

//报文类型及相关常量定义
public class PktCmd {
	public final static int PKT_AUTH = 1; // 认证报文
	public final static int PKT_AUTH_RES = 2; // 认证结果报文
	public final static int AUTH_FAIL = 200; // 认证失败
	public final static int AUTH_SUCCESS = 400; // 认证成功
}
