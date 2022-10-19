/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nio;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**;
 *
 * @author truon
 */
public class WatchFolderExample {
    public static void watch(Path path){
    FileSystem fs = path.getFileSystem();
        try (WatchService service = fs.newWatchService()){
            path.register(service, ENTRY_CREATE);
            while (true) {                
                WatchKey key = service.take();
                
                for (WatchEvent watchEvent : key.pollEvents()) {
                    WatchEvent.Kind kind = watchEvent.kind();
                    if (ENTRY_CREATE == kind) {
                       Path newPath = ((WatchEvent<Path>) watchEvent).context();
                        System.out.println("New path created: " + newPath);
                    }
                }
                if ((!key.reset())) break;
            }
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        Path path = Paths.get("D:\\Try_Text2");
        watch(path);
    }
}
