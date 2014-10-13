class Config {
    private Config() {}

    static JPL_PROJECTS = getJplProjects()
    static GUI_PROJECTS = getGuiProjects()
    static INTERPRET_PROJECTS = [':Interpret']

    private static getJplProjects() {
        def jpl = [
                1 : 1..16,
                2 : 1..18,
                3 : 1..12,
                4 : 1..6,
                5 : 1..2,
                6 : 1..5,
                7 : 1..3,
                8 : [],
                9 : 1..4,
                10 : 1..5,
                11 : 1..3,
                12 : 1..2,
                13 : 1..6,
                14 : 1..10,
                15 : [],
                16 : (1..12) - [6, 7, 8, 10],
                17 : 1..5,
                18 : [],
                19 : 1..2,
                20 : 1..11,
                21 : 1..7,
                22 : 1..15,
                23 : 1..3,
                24 : 1..3,
                25 : []
        ]

        def jplProjects = []

        jpl.each { chNum, exList ->
            exList.each { exNum ->
                def ch = String.format("%02d", chNum)
                def ex = String.format("%02d", exNum)
                jplProjects.add(":JPL:ch${ch}:ex${ch}_${ex}")
            }
        }

        return jplProjects
    }

    private static getGuiProjects() {
        def gui = [
                1 : 1..4,
                2 : 1..4
        ]

        def guiProjects = []

        gui.each { chNum, exList ->
            exList.each { exNum ->
                guiProjects.add(":GUI:${chNum}_${exNum}")
            }
        }

        return guiProjects
    }
}