// 代码生成时间: 2025-09-23 20:31:20
@Component
public class ExcelGeneratorComponent {

    private static final Logger logger = LoggerFactory.getLogger(ExcelGeneratorComponent.class);

    /**
     * 使用Apache POI生成Excel文件
     * 
     * @param data 数据源
     * @param fileName 文件名
     * @param sheetName 工作表名
     * @return 包含Excel文件的路径
     */
    public String generateExcel(List<Map<String, Object>> data, String fileName, String sheetName) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(sheetName);

            // 创建表头
            Row headerRow = sheet.createRow(0);
            int headerCellNum = 0;
            for (String header : data.get(0).keySet()) {
                Cell cell = headerRow.createCell(headerCellNum++);
                cell.setCellValue(header);
            }

            // 填充数据
            for (int i = 1; i < data.size() + 1; i++) {
                Row row = sheet.createRow(i);
                int cellNum = 0;
                for (Map.Entry<String, Object> entry : data.get(i - 1).entrySet()) {
                    Cell cell = row.createCell(cellNum++);
                    cell.setCellValue(String.valueOf(entry.getValue()));
                }
            }

            // 写入文件
            String path = "./" + fileName + ".xlsx";
            FileOutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

            return path;
        } catch (Exception e) {
            logger.error("Error while generating Excel file", e);
            throw new RuntimeException("Error while generating Excel file", e);
        }
    }
}
