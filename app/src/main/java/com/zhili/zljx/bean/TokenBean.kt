package com.zhili.zljx.bean

/**
 *@Auther KLOD
 *2023/4/3 15:00
 */

data class TokenBean(
    var access_token: String = "", // eyJhbGciOiJSUzI1NiIsImtpZCI6IjlGMjhFQThEMEE4QTBERDlDNTdDMDMzNjdDQ0U0NTEyMTg2NEI0NzBSUzI1NiIsInR5cCI6ImF0K2p3dCIsIng1dCI6Im55anFqUXFLRGRuRmZBTTJmTTVGRWhoa3RIQSJ9.eyJuYmYiOjE2ODA3NjM5NjMsImV4cCI6MTY4OTQwMzk2MywiaXNzIjoiaHR0cDovL3Rlc3Rzc28ueWl1eGl1LmNvbSIsImNsaWVudF9pZCI6IjkwODIzMTM0MzIyMDg2MzUwMDIiLCJzdWIiOiI0MDM4NTg3MTIwMDc5NDEiLCJhdXRoX3RpbWUiOjE2ODA3NjM5NjMsImlkcCI6ImxvY2FsIiwidXNlcl9udW1iZXIiOiI0MDM4NTg3MTIwMDc5NDEiLCJ1c2VyX21vYmlsZSI6IjE1MjU3OTI1MjYwIiwidXNlcl9uYW1lIjoiIiwidXNlcl9uaWNrX25hbWUiOiIxNTI1NzkyNTI2MCIsImp0aSI6IjUyRDVFNENCNUZEQzAwOThFQzAzMEY5NEZFMTIxRjk2IiwiaWF0IjoxNjgwNzYzOTYzLCJzY29wZSI6WyJDYXJTaG93Iiwib3BlbmlkIiwiUGF5bWVudCIsInByb2ZpbGUiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsic2hvcnRtZXNzYWdlX2NvZGUiXX0.qvX5qi9GqGraKlz0R5kjurJiiD4i1evNDnX85sV88NvugVJAX7ZU-M_6yHs6_6YWhWqLw3r3NP4h2VC21pYcN2pk5RaUVgTIJW_Ol4_jmJGyAA6MdhEPpHPQZiqfNPkMNDPtC8ryppuwZSInyjtd8FKpSwLkmt0Z5si4HPk-Eg68PauKipc5dPsh_JmTQ7E6iBIqefCmIfsw2ClLyp2FLFYAPJBDa5kTPiOHwtnLaCOmsYwVKIR32wOSUPXKqA_NHRytYMz_xFITwIryyMyId7MDMKdXbQLjOifskidg0CkNp01InoyU-ICNwHMgxWWaMwnEaj3Re0WJMq0lSG448A
    var expires_in: Int = 0, // 8640000
    var refresh_token: String = "", // 1D493E403BE0300B3BB0EED85F501EC1CF9C6D3D04C08CE2EDCF5C0B0144E2CF
    var scope: String = "", // CarShow offline_access openid Payment profile
    var token_type: String = "" // Bearer
)

