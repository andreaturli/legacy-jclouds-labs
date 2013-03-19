jclouds Google Cloud Storage Provider
======

This is a jclouds cloud provider modelled on the [Google Cloud Storage REST API](https://developers.google.com/storage/docs/getting-started).


# Running the live tests

    mvn clean install -Plive -Dtest.google-cloud-storage.identity=your_account_id@developer.gserviceaccount.com -Dtest.google-cloud-storage.credential=/path/to/private_key.pem -Dtest.google-cloud-storage.projectId=project_id
    
where *your_account_id* can be retrieved from *Overview* tab on [Google API Console](https://code.google.com/apis/console/), *private_key.pem* is your private key in PEM format and *project_id* is the [x-goog-project-id](https://developers.google.com/storage/docs/projects). 

### Notes

The credential is a private key, in pem format. It can be extracted from the p12 keystore obtained when creating a *Service Account* (Google API console > Api Access > Create another client ID > Service Account)

Read [this](https://developers.google.com/storage/docs/accesscontrol) for more details.
