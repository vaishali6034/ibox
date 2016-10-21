package edu.csupomona.cs585.ibox;
import static org.mockito.Mockito.*;

import java.io.File;

import org.junit.*;
import org.mockito.Mockito;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.model.FileList;

import edu.csupomona.cs585.ibox.sync.FileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import java.io.IOException;
import java.util.ArrayList;


public class TestGoogleDriveFileSyncManager {
	
	private Drive mockService;
	private GoogleDriveFileSyncManager driveFileSyncManager;
	
	@Before
	public void setup()
	{
		mockService=mock(Drive.class);
		driveFileSyncManager=new GoogleDriveFileSyncManager(mockService);
	}
	
	@Test
	public void addFileTest()throws IOException
	{
		File file=new File("E://test.txt");
	    com.google.api.services.drive.model.File value=new com.google.api.services.drive.model.File();
		//com.google.api.services.drive.model.File mockFile=mock(com.google.api.services.drive.model.File.class);
		value.setTitle(file.getName());
		value.setId("test");
		Files files=mock(Files.class);
		Files.Insert insert=mock(Files.Insert.class);
		when(mockService.files()).thenReturn(files);
		when(files.insert(Mockito.any(com.google.api.services.drive.model.File.class),Mockito.any(AbstractInputStreamContent.class))).thenReturn(insert);
		when(insert.execute()).thenReturn(value);
		
		driveFileSyncManager.addFile(file);
		verify(insert).execute();
		
	}
	
@Test(expected=IOException.class)
public void testAddFileIOException() throws IOException{
	File file=new File("E://test.txt");
    com.google.api.services.drive.model.File value=new com.google.api.services.drive.model.File();
	//com.google.api.services.drive.model.File mockFile=mock(com.google.api.services.drive.model.File.class);
	value.setTitle(file.getName());
	value.setId("test");
	Files files=mock(Files.class);
	Files.Insert insert=mock(Files.Insert.class);
	when(mockService.files()).thenReturn(files);
	when(files.insert(Mockito.any(com.google.api.services.drive.model.File.class),Mockito.any(AbstractInputStreamContent.class))).thenReturn(insert);
	when(insert.execute()).thenThrow(new IOException());
	
	driveFileSyncManager.addFile(file);
	
	
}

@Test
public void testGetField() throws IOException
{
	com.google.api.services.drive.model.File file=new com.google.api.services.drive.model.File();
	file.setTitle("text");
	file.setId("123");
	String filename = "text";
	FileList filelist=new FileList();
	ArrayList<com.google.api.services.drive.model.File> arraylist=new ArrayList<>();
	arraylist.add(file);
	
    filelist.setItems(arraylist);
	Files files= mock(Files.class);
	List list= mock(List.class) ;
	when(mockService.files()).thenReturn(files);
	when(files.list()).thenReturn(list);
	when(list.execute()).thenReturn(filelist);
	String result=driveFileSyncManager.getFileId("text");
	Assert.assertEquals("123", result);
	
	
}

@Test

	
public void updateFileTest() throws IOException
{
	File localFile=new File("E://test.txt");
	com.google.api.services.drive.model.File file=new com.google.api.services.drive.model.File();
	file.setTitle("test.txt");
	file.setId("123");
	String filename = "test.txt";
	FileList filelist=new FileList();
	java.util.List<com.google.api.services.drive.model.File> arraylist=new ArrayList<>();
	arraylist.add(file);
	
    filelist.setItems(arraylist);
	Files files= mock(Files.class);
	List list= mock(List.class) ;
	Files.Update update=mock(Files.Update.class);
	//when(localFile.getName()).thenReturn(filename);
	when(mockService.files()).thenReturn(files);
	when(files.list()).thenReturn(list);
	when(list.execute()).thenReturn(filelist);
	when(files.update(Mockito.any(String.class),Mockito.any(com.google.api.services.drive.model.File.class), Mockito.any(AbstractInputStreamContent.class))).thenReturn(update);
	when(update.execute()).thenReturn(file);
	driveFileSyncManager.updateFile(localFile);
	verify(update).execute();
	
	
	

}
/*@Test(expected=IOException.class)
public void updateFileTestWithException() throws IOException
{
	File localFile=new File("E://test.txt");
	com.google.api.services.drive.model.File file=new com.google.api.services.drive.model.File();
	file.setTitle("test.txt");
	file.setId("123");
	String filename = "test.txt";
	FileList filelist=new FileList();
	java.util.List<com.google.api.services.drive.model.File> arraylist=new ArrayList<>();
	arraylist.add(file);
	
    filelist.setItems(arraylist);
	Files files= mock(Files.class);
	List list= mock(List.class) ;
	Files.Update update=mock(Files.Update.class);
	when(localFile.getName()).thenReturn(filename);
	when(mockService.files()).thenReturn(files);
	when(files.list()).thenReturn(list);
	when(list.execute()).thenReturn(filelist);
	when(files.update(Mockito.any(String.class),Mockito.any(com.google.api.services.drive.model.File.class), Mockito.any(AbstractInputStreamContent.class))).thenReturn(update);
	when(update.execute()).thenThrow(new IOException());
	driveFileSyncManager.updateFile(localFile);
	verify(update).execute();
	
}*/




@Test
public void deleteFileTest() throws IOException
{
	File localFile=new File("E://test.txt");
	com.google.api.services.drive.model.File file=new com.google.api.services.drive.model.File();
	file.setTitle("test.txt");
	file.setId("123");
	String filename = "test.txt";
	FileList filelist=new FileList();
	java.util.List<com.google.api.services.drive.model.File> arraylist=new ArrayList<>();
	arraylist.add(file);
	
    filelist.setItems(arraylist);
	Files files= mock(Files.class);
	List list= mock(List.class) ;
	Files.Delete delete=mock(Files.Delete.class);
	when(localFile.getName()).thenReturn(filename);
	when(mockService.files()).thenReturn(files);
	when(files.list()).thenReturn(list);
	when(list.execute()).thenReturn(filelist);
	when(files.delete(Mockito.any(String.class))).thenReturn(delete);
	driveFileSyncManager.deleteFile(localFile);
	verify(delete).execute();
}

	
	
@Test(expected=IOException.class)

public void deleteFileTest1() throws IOException
{
	File localFile=new File("E://test.txt");
	com.google.api.services.drive.model.File file=new com.google.api.services.drive.model.File();
	file.setTitle("test.txt");
	file.setId("123");
	String filename = "test.txt";
	FileList filelist=new FileList();
	java.util.List<com.google.api.services.drive.model.File> arraylist=new ArrayList<>();
	arraylist.add(file);
	
    filelist.setItems(arraylist);
	Files files= mock(Files.class);
	List list= mock(List.class) ;
	Files.Delete delete=mock(Files.Delete.class);
	when(localFile.getName()).thenReturn(filename);
	when(mockService.files()).thenReturn(files);
	when(files.list()).thenReturn(list);
	when(list.execute()).thenReturn(filelist);
	when(files.delete(Mockito.any(String.class))).thenThrow(new IOException());
	driveFileSyncManager.deleteFile(localFile);
	verify(delete).execute();
		
	
}
}