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
import edu.csupomona.cs585.ibox.sync.GoogleDriveServiceProvider;

import java.io.IOException;
import java.util.ArrayList;

public class IntegrationTestGoogleDriveFileSyncManager {

/*	GoogleDriveFileSyncManager sync_manager = new GoogleDriveFileSyncManager(
			GoogleDriveServiceProvider.get().getGoogleDriveClient());

	@Test
	public void Integration_test_Adding_File() throws IOException {

		java.io.File javaio = new java.io.File("E://test.txt");
		sync_manager.addFile(javaio);
	}

@Test
public void Integration_test_updating_File() throws IOException {
	java.io.File object1 = new java.io.File("E://test.txt");
	sync_manager.updateFile(object1);
	
}

@Test
public void Integration_test_deleting_File() throws IOException {
	java.io.File object1 = new java.io.File("E://test.txt");
	sync_manager.deleteFile(object1);
	
}
*/

	
	
}