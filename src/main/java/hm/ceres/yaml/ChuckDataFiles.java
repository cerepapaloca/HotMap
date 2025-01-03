package hm.ceres.yaml;

import hm.ceres.HotMap;

public class ChuckDataFiles extends FilesYams {
    public ChuckDataFiles() {
        super("cacheMap", ChunkDataFile.class);
    }

    public void saveData(){
        for (int x = 0 ; x < HotMap.MAP.length ; x++){
            for (int z = 0 ; z < HotMap.MAP[x].length ; z++){
                if (HotMap.MAP[x][z] != null){
                    int xR = x - HotMap.width /2;
                    int zR = z - HotMap.height /2;
                    FileYaml fileYaml = getConfigFile(xR + "." + zR, true);
                    fileYaml.saveData();
                }
            }
        }
    }

    public void loadData(){
        for (FileYaml file : getConfigFiles()){
            file.loadData();
        }
    }
}
