package mods.mednis.id.lv.customemotes.emotes;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmoteRegistry {
  private final Logger LOGGER = LogManager.getLogger("customemotes.emotes.EmoteRegistry");
  
  private static EmoteRegistry emoteRegistry;
  
  public ConcurrentHashMap<Integer, Emote> emoteIdMap;
  
  public ConcurrentHashMap<String, Emote> emoteMap;
  
  public static EmoteRegistry getInstance() {
    if (emoteRegistry == null)
      emoteRegistry = new EmoteRegistry(); 
    return emoteRegistry;
  }
  
  public void init() {
    this.emoteIdMap = new ConcurrentHashMap<>();
    this.emoteMap = new ConcurrentHashMap<>();
    try {

      // --- Regular Emotes ---
      // GP Emotes
      registerEmote(new Emote("EaterLook", "gp/eaterlook.png"));
      registerEmote(new Emote("FREEDOOOM", "gp/freedooom.png"));
      registerEmote(new Emote("KingCrown", "gp/kingcrown.png"));
      registerEmote(new Emote("KingHearts", "gp/kinghearts.png"));
      registerEmote(new Emote("KingYell", "gp/kingyell.png"));
      registerEmote(new Emote("KingLaugh", "gp/kinglaugh.png"));
      registerEmote(new Emote("KingMlem", "gp/kingmlem.png"));
      registerEmote(new Emote("KingPog", "gp/kingpog.png"));
      registerEmote(new Emote("KingSadge", "gp/kingsadge.png"));
      registerEmote(new Emote("KingShady", "gp/kingshady.png"));
      registerEmote(new Emote("KingWut", "gp/kingwut.png"));

      // General Emotes
      registerEmote(new Emote("catCry", "general/cat-cry.png"));
      registerEmote(new Emote("catScream", "general/cat-scream.png"));
      registerEmote(new Emote("madge", "general/madge.png"));
      registerEmote(new Emote("poggies", "general/poggies.png"));
      registerEmote(new Emote("pepeCry", "general/pepe-hands.png"));
      registerEmote(new Emote("bedge", "general/bedge.png"));

      // Mednis
      registerEmote(new Emote("HEH", "mednis/heh.png"));
      registerEmote(new Emote("ChefsKiss", "mednis/chefskiss.png"));
      registerEmote(new Emote("ph1lYEP", "mednis/ph1lyep.png"));
      registerEmote(new Emote("KEKW", "mednis/kekw.png"));

      // Moth
      registerEmote(new Emote("blueeye", "moth/blureyes.png"));
      registerEmote(new Emote("squintsat", "moth/squinteyes.png"));

      // Samus
      registerEmote(new Emote("HeartScream", "samus/heartscream.png"));
      registerEmote(new Emote("ph1lPIG", "samus/ph1lpig.png"));
      registerEmote(new Emote("VeryConcern", "samus/veryconcern.png"));

      // Kizen
      registerEmote(new Emote("WoahHeyPal", "kizen/woahheypal.png"));
      registerEmote(new Emote("SansSmug", "kizen/sanssmug.png"));
      registerEmote(new Emote("SansStare", "kizen/sanstare.png"));

      // --- Animated Emotes ---
      // GP Emotes
      registerEmote(new Emote("PetTheGhostlly", "animated/gp/pettheghostlly.png", 60));
      registerEmote(new Emote("PetTheLukas", "animated/gp/petthelukas.png", 60));
      registerEmote(new Emote("PetTheMoth", "animated/gp/petthemoth.png", 60));
      registerEmote(new Emote("PetTheSamus", "animated/gp/petthesamus.png", 60));
      registerEmote(new Emote("PetTheGod", "animated/gp/petthemednis.png", 60));
      registerEmote(new Emote("ParaTap", "animated/gp/paratap.png", 70));

      // Regular Emotes
      registerEmote(new Emote("catJAM", "animated/general/catjam.png", 40));
      registerEmote(new Emote("NODDERS", "animated/general/nodders.png", 20));
      registerEmote(new Emote("peepoShy", "animated/general/peepo-shy.png", 250));
      registerEmote(new Emote("PepeLaugh", "animated/general/pepe-laugh.png", 20));
      registerEmote(new Emote("NOPERS", "animated/general/nopers.png", 70));

      // Mednis
      registerEmote(new Emote("dogJAM", "animated/mednis/dogjam.png", 70));
      registerEmote(new Emote("blink", "animated/mednis/blink.png", 30));
      registerEmote(new Emote("catKISS", "animated/mednis/catkiss.png", 60));
      registerEmote(new Emote("hypeE", "animated/mednis/hypee.png", 30));
      registerEmote(new Emote("monkaSHAKE", "animated/mednis/monkashake.png", 40));

      // Samus
      registerEmote(new Emote("Blobble", "animated/samus/blobble.png", 20));
      registerEmote(new Emote("BoneZone", "animated/samus/bonezone.png", 40));
      registerEmote(new Emote("BongoTap", "animated/samus/bongotap.png", 50));
      registerEmote(new Emote("PressF", "animated/samus/pressf.png", 30));
      registerEmote(new Emote("StickBug", "animated/samus/stickbug.png", 60));
      registerEmote(new Emote("ThisIsFine", "animated/samus/thisisfine.png", 10));
      registerEmote(new Emote("Popza", "animated/samus/popza.png", 90));




    } catch (Exception e) {
      this.LOGGER.error("init(): Failed to load emotes", e);
    } 
  }
  
  public void registerEmote(Emote emote) throws Exception {
    if (this.emoteMap.containsKey(emote.getName()))
      throw new Exception("Emote with the name " + emote.getName() + " already exists, failed to register"); 
    this.emoteIdMap.put(emote.getId(), emote);
    this.emoteMap.put(emote.getName(), emote);
  }
  
  public Emote getEmoteById(int id) {
    return this.emoteIdMap.get(id);
  }
  
  public Emote getEmoteByName(String name) {
    return this.emoteMap.get(name);
  }
  
  public Collection<String> getEmoteSuggestions() {
    return Lists.newArrayList(this.emoteMap.keys().asIterator()).stream().map(name -> ":" + name + ":").collect(Collectors.toList());
  }
}