// 代码生成时间: 2025-09-24 16:39:30
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MemoryAnalysisComponent {

    private final MemoryMXBean memoryMXBean;

    public MemoryAnalysisComponent() {
        this.memoryMXBean = ManagementFactory.getMemoryMXBean();
    }

    /**
     * Analyzes the memory usage, returning a summary of the heap and non-heap memory usage.
     *
     * @return A map containing memory usage data.
     */
    public Map<String, MemoryUsage> analyzeMemoryUsage() {
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        Map<String, MemoryUsage> memoryUsageMap = Map.of(
            "heap", heapMemoryUsage,
            "nonHeap", nonHeapMemoryUsage
        );

        return memoryUsageMap;
    }

    /**
     * Get the list of directories in the application home directory.
     *
     * @return A list of directory paths.
     */
    public List<String> getApplicationHomeDirectories() {
        ApplicationHome home = new ApplicationHome();
        File homeDirectory = home.getDir();
        File[] directories = homeDirectory.listFiles(File::isDirectory);

        if (directories == null) {
            throw new IllegalStateException("Unable to list directories in application home.");
        }

        return List.of(directories).stream().map(File::getPath).collect(Collectors.toList());
    }

    /**
     * Get the list of files in the application home directory.
     *
     * @return A list of file paths.
     */
    public List<String> getApplicationHomeFiles() {
        ApplicationHome home = new ApplicationHome();
        File homeDirectory = home.getDir();
        File[] files = homeDirectory.listFiles(File::isFile);

        if (files == null) {
            throw new IllegalStateException("Unable to list files in application home.");
        }

        return List.of(files).stream().map(File::getPath).collect(Collectors.toList());
    }
}
