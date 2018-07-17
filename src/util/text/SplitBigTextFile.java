package util.text;


import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;


/**
 * Java大文本文件切割
 * 
 * 工作中遇到要将大文本（500M以上）文件切割成小文本文件，再利用多线程来提高上传效率的问题。
 * 如果直接用readLine，则效率很差。改进的方式是先按照大小来进行切分，再寻找换行符，以保证每行记录的完整性。
 * 
 * 参考  https://blog.csdn.net/lwl550660646/article/details/46739651
 * @author Yan
 *
 */
public class SplitBigTextFile {

	// 分割后每个文件的大小为10M
	static final int byteSize = 10 * 1024 * 1024;

    public void run(String originFile, String targetDirectoryPath) {
        File sourceFile = new File(originFile);  
        File targetFile = new File(targetDirectoryPath);  
        if (!sourceFile.exists() || sourceFile.isDirectory()) {
            return;  
        }
        if (targetFile.exists()) {
            if (!targetFile.isDirectory()) {
                return;  
            }
        } else {
            targetFile.mkdirs();  
        }

        RandomAccessFile rFile;
        OutputStream os;
        try {
            rFile = new RandomAccessFile(originFile, "r");
            long fileLength = rFile.length();
            long startPos = 0;
            long fileSeq = 1;
            while(startPos < fileLength){
                rFile.seek(startPos + byteSize);
                int extra = eofOrNextCRLFInterval(rFile);
                int curbyteSize = byteSize + extra;

                rFile.seek(startPos);
                byte[] b = new byte[curbyteSize];
                int s = rFile.read(b);
                os = new FileOutputStream(targetFile.getAbsolutePath() + "/" +  sourceFile.getName().replaceAll("[.][^.]+$", "") +"_" + fileSeq +".sql");
                os.write(b, 0, s);
                os.flush();
                os.close();
                startPos += curbyteSize;
                fileSeq ++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int eofOrNextCRLFInterval(RandomAccessFile rFile) throws IOException{
        boolean isCRLF = false;
        int interval = 0;
        while(!isCRLF){
            try{
                interval ++;
                int readByte =  rFile.readByte();
                if(readByte == 0X0A){
                    isCRLF = true;
                }
            }catch(EOFException e){
                isCRLF = true;
                break;
            }
        }
        return interval;
    }


    public static void main(String[] args){
        String sourceFile = "C:\\Users\\Yan\\Desktop\\zhihu\\seven.sql";
        String targetFilePath = "C:\\Users\\Yan\\Desktop\\zhihu\\split";
        SplitBigTextFile s = new SplitBigTextFile();
        long start1 = System.currentTimeMillis();
        System.out.println(start1);
        s.run(sourceFile, targetFilePath);
        long start2 = System.currentTimeMillis();
        System.out.println(start2);
        System.out.println((start2 - start1)/1000.00 + "  second");
    }
}
