package com.honorassistant.app.data.datasource

import com.honorassistant.app.data.models.LoreLocation

object LoreDataSource {
    fun provideLore(): List<LoreLocation> = listOf(
        LoreLocation("1", "长城守卫军", "抵御魔种的雄关...", "https://picsum.photos/seed/great_wall/800/400",
            listOf("花木兰", "铠"), listOf("花木兰代父从军...")),
        LoreLocation("2", "逐鹿之野", "群雄并起...", "https://picsum.photos/seed/battlefield/800/400",
            listOf("吕布", "貂蝉"), listOf("吕布无双...")),
        LoreLocation("3", "长安城", "盛世大唐...", "https://picsum.photos/seed/changan/800/400",
            listOf("李白", "狄仁杰"), listOf("李白是剑客..."))
    )
}
