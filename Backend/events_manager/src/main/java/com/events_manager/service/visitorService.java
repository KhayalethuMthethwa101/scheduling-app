import org.springframework.stereotype.Service;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.CollectionReference;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import com.events_manager.model.*;

@Service
public class visitorService {
    @Autowired
    private Firestore firestore;

    public String signUp(visitor visitorDto) throws ExecutionException, InterruptedException {
        CollectionReference visitors = firestore.collection("visitors");
        ApiFuture<WriteResult> future = visitors.document(visitorDto.getId()).set(visitorDto);
        future.get();
        return "Visitor signed up successfully!";
    }
}