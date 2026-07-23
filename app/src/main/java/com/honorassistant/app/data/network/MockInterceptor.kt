package com.honorassistant.app.data.network

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val responseBody = when {
            uri.contains("heroes") -> HEROES_JSON
            else -> "[]"
        }
        return Response.Builder()
            .code(200)
            .message("OK")
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .body(responseBody.toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }

    companion object {
        private val HEROES_JSON = """
        [
          {
            "id": "1",
            "name": "李白",
            "title": "青莲剑仙",
            "lore": "十步杀一人，千里不留行。事了拂衣去，深藏身与名。李白是峡谷中最潇洒的刺客，剑法飘逸，来去如风。",
            "role": "刺客",
            "difficulty": 3,
            "splashArtUrl": "https://picsum.photos/seed/li_bai/600/800",
            "skills": [
              {"name": "将进酒", "description": "突进并对目标造成伤害，短时间内可再次使用。", "iconUrl": "", "cooldown": "5s", "cost": "无"},
              {"name": "青莲剑歌", "description": "挥剑形成剑气，对范围内的敌人造成伤害。", "iconUrl": "", "cooldown": "8s", "cost": "无"}
            ],
            "skins": [
              {"name": "千年之狐", "tier": "史诗", "imageUrl": ""},
              {"name": "凌云仙道", "tier": "传说", "imageUrl": ""}
            ]
          },
          {
            "id": "2",
            "name": "花木兰",
            "title": "长城守护者",
            "lore": "谁说女子不如男？花木兰代父从军，驰骋沙场，以无双剑法守护长城，成为峡谷中最勇猛的战士。",
            "role": "战士",
            "difficulty": 3,
            "splashArtUrl": "https://picsum.photos/seed/hua_mulan/600/800",
            "skills": [
              {"name": "空裂斩", "description": "挥剑斩出剑气，对路径上的敌人造成伤害。", "iconUrl": "", "cooldown": "6s", "cost": "无"},
              {"name": "剑舞", "description": "快速对周围敌人发动多段攻击。", "iconUrl": "", "cooldown": "10s", "cost": "无"}
            ],
            "skins": [
              {"name": "水晶猎龙者", "tier": "史诗", "imageUrl": ""},
              {"name": "花木兰原皮", "tier": "普通", "imageUrl": ""}
            ]
          },
          {
            "id": "3",
            "name": "诸葛亮",
            "title": "绝代智谋",
            "lore": "运筹帷幄之中，决胜千里之外。诸葛亮以智谋闻名，在峡谷中以法术控制战场，是队伍中不可或缺的智者。",
            "role": "法师",
            "difficulty": 2,
            "splashArtUrl": "https://picsum.photos/seed/zhuge_liang/600/800",
            "skills": [
              {"name": "东风破袭", "description": "召唤东风，对目标区域造成持续伤害并减速。", "iconUrl": "", "cooldown": "4s", "cost": "无"},
              {"name": "八卦阵", "description": "在周围布下八卦阵，减速并伤害踏入的敌人。", "iconUrl": "", "cooldown": "12s", "cost": "无"}
            ],
            "skins": [
              {"name": "星航指挥官", "tier": "史诗", "imageUrl": ""},
              {"name": "白羽扇", "tier": "普通", "imageUrl": ""}
            ]
          },
          {
            "id": "4",
            "name": "吕布",
            "title": "天下第一",
            "lore": "人中吕布，马中赤兔。吕布是三国时代最强的武将，手持方天画戟，无人能敌。",
            "role": "战士",
            "difficulty": 2,
            "splashArtUrl": "https://picsum.photos/seed/lv_bu/600/800",
            "skills": [
              {"name": "画戟横扫", "description": "横扫周围区域，对敌人造成大量伤害并击飞。", "iconUrl": "", "cooldown": "7s", "cost": "无"},
              {"name": "并州儿郎", "description": "冲向目标，造成伤害并短暂控制。", "iconUrl": "", "cooldown": "9s", "cost": "无"}
            ],
            "skins": [
              {"name": "无双战神", "tier": "传说", "imageUrl": ""}
            ]
          },
          {
            "id": "5",
            "name": "狄仁杰",
            "title": "神探大人",
            "lore": "元芳，你怎么看？狄仁杰是大唐最出色的神探，在峡谷中以精准的箭法压制敌人。",
            "role": "射手",
            "difficulty": 1,
            "splashArtUrl": "https://picsum.photos/seed/di_renjie/600/800",
            "skills": [
              {"name": "七星射", "description": "快速射出多支箭矢，每支造成物理伤害。", "iconUrl": "", "cooldown": "3s", "cost": "无"},
              {"name": "大破军", "description": "释放强力一箭，穿透路径上的所有敌人。", "iconUrl": "", "cooldown": "15s", "cost": "无"}
            ],
            "skins": [
              {"name": "破军之弓", "tier": "史诗", "imageUrl": ""}
            ]
          }
        ]
        """.trimIndent()
    }
}
