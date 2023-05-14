package com.ltu.m7019e.v23.themoviedb.database

import com.ltu.m7019e.v23.themoviedb.model.Genre
import com.ltu.m7019e.v23.themoviedb.model.Movie

class Movies {
    val list = mutableListOf<Movie>()

    init {
        /*
        list.add(
            Movie(
                502356,
                "The Super Mario Bros. Movie",
                listOf(
                    Genre(1,"Animation"),
                    Genre(2,"Adventure"),
                    Genre(3,"Family"),
                    Genre(4,"Fantasy"),
                    Genre(5,"Comedy")
                ),
                "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                "/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg",
                "2023-04-05",
                "https://www.thesupermariobros.movie/",
                "tt6718170",
                "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
            )
        )
        list.add(
            Movie(
                594767,
                "Shazam! Fury of the Gods",
                listOf(
                    Genre(4,"Fantasy"),
                    Genre(6,"Action"),
                    Genre(5,"Comedy")
                ),
                "/3GrRgt6CiLIUXUtoktcv1g2iwT5.jpg",
                "/wybmSmviUXxlBmX44gtpow5Y9TB.jpg",
                "2023-03-15",
                "",
                "tt10151854",
                "Billy Batson and his foster siblings, who transform into superheroes by saying \"Shazam!\", are forced to get back into action and fight the Daughters of Atlas, who they must stop from using a weapon that could destroy the world."            )
        )
        list.add(
            Movie(
                76600,
                "Avatar: The Way of Water",
                listOf(
                    Genre(7,"Science Fiction"),
                    Genre(2,"Adventure"),
                    Genre(6,"Action")
                ),
                "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
                "/ovM06PdF3M8wvKb06i4sjW3xoww.jpg",
                "2022-12-14",
                "https://www.avatar.com/movies/avatar-the-way-of-water",
                "tt1630029",
                "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure."            )
        )
        list.add(
            Movie(
                677179,
                "Creed III",
                listOf(
                    Genre(8, "Drama"),
                    Genre(6, "Action")
                ),
                "/cvsXj3I9Q2iyyIo95AecSd1tad7.jpg",
                "/5i6SjyDbDWqyun8klUuCxrlFbyw.jpg",
                "2023-03-01",
                "https://www.mgmstudios.com/creed-iii",
                "tt11145118",
                "After dominating the boxing world, Adonis Creed has been thriving in both his career and family life. When a childhood friend and former boxing prodigy, Damien Anderson, resurfaces after serving a long sentence in prison, he is eager to prove that he deserves his shot in the ring. The face-off between former friends is more than just a fight. To settle the score, Adonis must put his future on the line to battle Damien — a fighter who has nothing to lose."            )
        )
        list.add(
            Movie(
                700391,
                "65",
                listOf(
                    Genre(7, "Science Fiction"),
                    Genre(2, "Adventure"),
                    Genre(9, "Thriller"),
                    Genre(6, "Action")
                ),
                "/rzRb63TldOKdKydCvWJM8B6EkPM.jpg",
                "/eSVu1FvGPy86TDo4hQbpuHx55DJ.jpg",
                "2023-03-02",
                "https://www.65.movie",
                "tt12261776",
                "65 million years ago, the only 2 survivors of a spaceship from Somaris that crash-landed on Earth must fend off dinosaurs and reach the escape vessel in time before an imminent asteroid strike threatens to destroy the planet."
            )
        )
        list.add(
            Movie(
                502356,
                "The Super Mario Bros. Movie",
                listOf(
                    Genre(1,"Animation"),
                    Genre(2,"Adventure"),
                    Genre(3,"Family"),
                    Genre(4,"Fantasy"),
                    Genre(5,"Comedy")
                ),
                "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                "/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg",
                "2023-04-05",
                "https://www.thesupermariobros.movie/",
                "tt6718170",
                "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
            )
        )
        list.add(
            Movie(
                594767,
                "Shazam! Fury of the Gods",
                listOf(
                    Genre(4,"Fantasy"),
                    Genre(6,"Action"),
                    Genre(5,"Comedy")
                ),
                "/3GrRgt6CiLIUXUtoktcv1g2iwT5.jpg",
                "/wybmSmviUXxlBmX44gtpow5Y9TB.jpg",
                "2023-03-15",
                "",
                "tt10151854",
                "Billy Batson and his foster siblings, who transform into superheroes by saying \"Shazam!\", are forced to get back into action and fight the Daughters of Atlas, who they must stop from using a weapon that could destroy the world."            )
        )
        list.add(
            Movie(
                76600,
                "Avatar: The Way of Water",
                listOf(
                    Genre(7,"Science Fiction"),
                    Genre(2,"Adventure"),
                    Genre(6,"Action")
                ),
                "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
                "/ovM06PdF3M8wvKb06i4sjW3xoww.jpg",
                "2022-12-14",
                "https://www.avatar.com/movies/avatar-the-way-of-water",
                "tt1630029",
                "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure."            )
        )
        list.add(
            Movie(
                677179,
                "Creed III",
                listOf(
                    Genre(8, "Drama"),
                    Genre(6, "Action")
                ),
                "/cvsXj3I9Q2iyyIo95AecSd1tad7.jpg",
                "/5i6SjyDbDWqyun8klUuCxrlFbyw.jpg",
                "2023-03-01",
                "https://www.mgmstudios.com/creed-iii",
                "tt11145118",
                "After dominating the boxing world, Adonis Creed has been thriving in both his career and family life. When a childhood friend and former boxing prodigy, Damien Anderson, resurfaces after serving a long sentence in prison, he is eager to prove that he deserves his shot in the ring. The face-off between former friends is more than just a fight. To settle the score, Adonis must put his future on the line to battle Damien — a fighter who has nothing to lose."            )
        )
        list.add(
            Movie(
                700391,
                "65",
                listOf(
                    Genre(7, "Science Fiction"),
                    Genre(2, "Adventure"),
                    Genre(9, "Thriller"),
                    Genre(6, "Action")
                ),
                "/rzRb63TldOKdKydCvWJM8B6EkPM.jpg",
                "/eSVu1FvGPy86TDo4hQbpuHx55DJ.jpg",
                "2023-03-02",
                "https://www.65.movie",
                "tt12261776",
                "65 million years ago, the only 2 survivors of a spaceship from Somaris that crash-landed on Earth must fend off dinosaurs and reach the escape vessel in time before an imminent asteroid strike threatens to destroy the planet."
            )
        )


         */

    }
}
