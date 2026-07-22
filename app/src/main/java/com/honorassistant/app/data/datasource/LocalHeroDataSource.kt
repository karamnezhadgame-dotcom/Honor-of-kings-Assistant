package com.honorassistant.app.data.datasource

import com.honorassistant.app.data.models.Hero
import com.honorassistant.app.data.models.Skill
import com.honorassistant.app.data.models.Skin

object LocalHeroDataSource {
    fun provideHeroes(): List<Hero> = listOf(
        Hero("1", "李白", "青莲剑仙", "十步杀一人...", "刺客", 3, "https://picsum.photos/seed/li_bai/600/800",
            listOf(Skill("将进酒", "突进...", "", "5s", "无")), listOf(Skin("千年之狐", "史诗", ""))),
        Hero("2", "花木兰", "长城守护者", "谁说女子不如男...", "战士", 3, "https://picsum.photos/seed/hua_mulan/600/800",
            listOf(Skill("空裂斩", "挥剑...", "", "6s", "无")), listOf(Skin("水晶猎龙者", "史诗", ""))),
        Hero("3", "诸葛亮", "绝代智谋", "运筹帷幄...", "法师", 2, "https://picsum.photos/seed/zhuge_liang/600/800",
            listOf(Skill("东风破袭", "东风...", "", "4s", "无")), listOf(Skin("星航指挥官", "史诗", "")))
    )
}
