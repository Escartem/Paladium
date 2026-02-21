package fr.paladium.palamod.modules.luckyblock.utils;

import com.allatori.annotations.DoNotRename;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.config.ConfigManager;
import fr.paladium.palamod.modules.luckyblock.luckyevents.AbandonedBase;
import fr.paladium.palamod.modules.luckyblock.luckyevents.AddMoney;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Admins;
import fr.paladium.palamod.modules.luckyblock.luckyevents.AirMin;
import fr.paladium.palamod.modules.luckyblock.luckyevents.AllInOne;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Analyste;
import fr.paladium.palamod.modules.luckyblock.luckyevents.AngryWither;
import fr.paladium.palamod.modules.luckyblock.luckyevents.AnvilHead;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ArachnoTrap;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Armaguedon;
import fr.paladium.palamod.modules.luckyblock.luckyevents.BadFace;
import fr.paladium.palamod.modules.luckyblock.luckyevents.BadaBoum;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Balaclava;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Banana;
import fr.paladium.palamod.modules.luckyblock.luckyevents.BaseDeco;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Beacon;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Bee;
import fr.paladium.palamod.modules.luckyblock.luckyevents.BigDyna;
import fr.paladium.palamod.modules.luckyblock.luckyevents.BiomePainter;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Blindness;
import fr.paladium.palamod.modules.luckyblock.luckyevents.BoatFurnace;
import fr.paladium.palamod.modules.luckyblock.luckyevents.BodyGuard;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Bol;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Boom;
import fr.paladium.palamod.modules.luckyblock.luckyevents.BoomMobs;
import fr.paladium.palamod.modules.luckyblock.luckyevents.BouBam;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Bouseul;
import fr.paladium.palamod.modules.luckyblock.luckyevents.BreakEars;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Caballo;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Calcium;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Camouflage;
import fr.paladium.palamod.modules.luckyblock.luckyevents.CancelView;
import fr.paladium.palamod.modules.luckyblock.luckyevents.CatDetector;
import fr.paladium.palamod.modules.luckyblock.luckyevents.CaveHelmet;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ChaosProfessor;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ChatDetector;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ChewedWork;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ChickenShoot;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ClearXP;
import fr.paladium.palamod.modules.luckyblock.luckyevents.CoffreFort;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ColorfulLamp;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Coloriage;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Commando;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Confined;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Consolation;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Coucou;
import fr.paladium.palamod.modules.luckyblock.luckyevents.CowPlayer;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Crash;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Creepesque;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DirtIsGood;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DirtyInventory;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DivinityRank;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DiviseXP;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DontBreak;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DontDrinkThat;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DontDrinkThatBad;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DontMove;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DontTellAnyone;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DoubleXP;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Duplicata;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Elephant;
import fr.paladium.palamod.modules.luckyblock.luckyevents.End;
import fr.paladium.palamod.modules.luckyblock.luckyevents.EnderChest;
import fr.paladium.palamod.modules.luckyblock.luckyevents.EndiumBlock;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Enigme;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Expalaosion;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Explose;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Explosif;
import fr.paladium.palamod.modules.luckyblock.luckyevents.EyeBattle;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FactionLeave;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FakeMoney;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FakePoweredCreeper;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FakeStuffEndium;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FakeTNT;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FastLearner;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FireMan;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FishBowl;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FlashMcQueen;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Flou;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FreeMoreXP;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FreeXP;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FrozenGround;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FuzeCoordinates;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FuzePersonal;
import fr.paladium.palamod.modules.luckyblock.luckyevents.FuzeShop;
import fr.paladium.palamod.modules.luckyblock.luckyevents.GeneralDrudgery;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Geyser;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Ghast;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Ghost;
import fr.paladium.palamod.modules.luckyblock.luckyevents.GodPickaxeMax;
import fr.paladium.palamod.modules.luckyblock.luckyevents.GolemCrasseux;
import fr.paladium.palamod.modules.luckyblock.luckyevents.GolemUpgrade;
import fr.paladium.palamod.modules.luckyblock.luckyevents.GoodButNotTooMuch;
import fr.paladium.palamod.modules.luckyblock.luckyevents.GrandmaMix;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Grappin;
import fr.paladium.palamod.modules.luckyblock.luckyevents.GrosRelou;
import fr.paladium.palamod.modules.luckyblock.luckyevents.HappyNewYear;
import fr.paladium.palamod.modules.luckyblock.luckyevents.HaveFun;
import fr.paladium.palamod.modules.luckyblock.luckyevents.HeadOnShoulders;
import fr.paladium.palamod.modules.luckyblock.luckyevents.HideHead;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Hole;
import fr.paladium.palamod.modules.luckyblock.luckyevents.HowDidYouGetThat;
import fr.paladium.palamod.modules.luckyblock.luckyevents.HunterPlant;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ILoveCats;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ILoveYouEarly;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Injouable;
import fr.paladium.palamod.modules.luckyblock.luckyevents.InstantBreakUp;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Inversion;
import fr.paladium.palamod.modules.luckyblock.luckyevents.InvisibleHuman;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Invocator;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ItsHigh;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ItsHot;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ItsNotAllowed;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Jackpot;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Jetpack;
import fr.paladium.palamod.modules.luckyblock.luckyevents.JimmyHendyx;
import fr.paladium.palamod.modules.luckyblock.luckyevents.JohnTheRipper;
import fr.paladium.palamod.modules.luckyblock.luckyevents.KawaiiTnt;
import fr.paladium.palamod.modules.luckyblock.luckyevents.KobeBryan;
import fr.paladium.palamod.modules.luckyblock.luckyevents.LaHaut;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Labyrinthe;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Lag;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Lasso;
import fr.paladium.palamod.modules.luckyblock.luckyevents.LebronJames;
import fr.paladium.palamod.modules.luckyblock.luckyevents.LegendaryStone;
import fr.paladium.palamod.modules.luckyblock.luckyevents.LessKawaiiTnt;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Lucky;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MagicPotion;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Magnetic;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MarioMushroom;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MegaBoom;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MegaCoffreFort;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MegaFastLearner;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MegaTethanos;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Meter;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MinedTerrain;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MineralRush;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MineralShower;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MobTower;
import fr.paladium.palamod.modules.luckyblock.luckyevents.MyNameIsFuzeIII;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Nausea;
import fr.paladium.palamod.modules.luckyblock.luckyevents.NervousBatman;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Nether;
import fr.paladium.palamod.modules.luckyblock.luckyevents.NetherEnabled;
import fr.paladium.palamod.modules.luckyblock.luckyevents.NightVision;
import fr.paladium.palamod.modules.luckyblock.luckyevents.NoobTrap;
import fr.paladium.palamod.modules.luckyblock.luckyevents.NotTooFar;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ObsiTrap;
import fr.paladium.palamod.modules.luckyblock.luckyevents.OnTheMoon;
import fr.paladium.palamod.modules.luckyblock.luckyevents.OneMillionPlayer;
import fr.paladium.palamod.modules.luckyblock.luckyevents.OneShot;
import fr.paladium.palamod.modules.luckyblock.luckyevents.OrangeOrBlue;
import fr.paladium.palamod.modules.luckyblock.luckyevents.OverNineThousand;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PalaChat;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PalaFakeOre;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PalaIsHere;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PalaRelou;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PalaRelouGreen;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PalaTower;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Paladin;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PaladiumStrange;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PeaceNLove;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PereCastor;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PetRock;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Picasso;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PigBoots;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PigChestplate;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PigHelmet;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PigLeggings;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PiggyRodeo;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Poulay;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PoulpoGun;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PricklySheep;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Princess;
import fr.paladium.palamod.modules.luckyblock.luckyevents.PutFire;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Pyrobarbare;
import fr.paladium.palamod.modules.luckyblock.luckyevents.RainbowArmor;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Rantamplan;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Rayman;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ReadyForEverything;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Reflexe;
import fr.paladium.palamod.modules.luckyblock.luckyevents.RemoveMoney;
import fr.paladium.palamod.modules.luckyblock.luckyevents.RemovedFromGame;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Reveal;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Rodshild;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Rotate;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Roulette;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Seum;
import fr.paladium.palamod.modules.luckyblock.luckyevents.SleepingBag;
import fr.paladium.palamod.modules.luckyblock.luckyevents.SlimePad;
import fr.paladium.palamod.modules.luckyblock.luckyevents.SlimeTower;
import fr.paladium.palamod.modules.luckyblock.luckyevents.SmokedSalmon;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Soda;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Sonic;
import fr.paladium.palamod.modules.luckyblock.luckyevents.SpaceFood;
import fr.paladium.palamod.modules.luckyblock.luckyevents.SpamTp;
import fr.paladium.palamod.modules.luckyblock.luckyevents.SpamTpaHere;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Spawner;
import fr.paladium.palamod.modules.luckyblock.luckyevents.SquidGameHelmet;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Stalactites;
import fr.paladium.palamod.modules.luckyblock.luckyevents.StarFish;
import fr.paladium.palamod.modules.luckyblock.luckyevents.StatsStone;
import fr.paladium.palamod.modules.luckyblock.luckyevents.StayAtHome;
import fr.paladium.palamod.modules.luckyblock.luckyevents.SteveJobs;
import fr.paladium.palamod.modules.luckyblock.luckyevents.StormLighting;
import fr.paladium.palamod.modules.luckyblock.luckyevents.SuperMan;
import fr.paladium.palamod.modules.luckyblock.luckyevents.TeamKiller;
import fr.paladium.palamod.modules.luckyblock.luckyevents.TeamTree;
import fr.paladium.palamod.modules.luckyblock.luckyevents.TeleportYOne;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Tethanos;
import fr.paladium.palamod.modules.luckyblock.luckyevents.TextureNotFound;
import fr.paladium.palamod.modules.luckyblock.luckyevents.TimeMachine;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Todoumdoum;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Tombe;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Trap;
import fr.paladium.palamod.modules.luckyblock.luckyevents.TrapString;
import fr.paladium.palamod.modules.luckyblock.luckyevents.TreasureMapBad;
import fr.paladium.palamod.modules.luckyblock.luckyevents.TreasureMapGood;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Triforce;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Trompette;
import fr.paladium.palamod.modules.luckyblock.luckyevents.TryAgain;
import fr.paladium.palamod.modules.luckyblock.luckyevents.UltraCoffreFort;
import fr.paladium.palamod.modules.luckyblock.luckyevents.UnRank;
import fr.paladium.palamod.modules.luckyblock.luckyevents.VillaNul;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Voyant;
import fr.paladium.palamod.modules.luckyblock.luckyevents.WalkInMusic;
import fr.paladium.palamod.modules.luckyblock.luckyevents.WeightedBoots;
import fr.paladium.palamod.modules.luckyblock.luckyevents.WetFirecracker;
import fr.paladium.palamod.modules.luckyblock.luckyevents.WhatDidYouEat;
import fr.paladium.palamod.modules.luckyblock.luckyevents.WhoAreYou;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Wifi;
import fr.paladium.palamod.modules.luckyblock.luckyevents.WitherHead;
import fr.paladium.palamod.modules.luckyblock.luckyevents.WolfAlly;
import fr.paladium.palamod.modules.luckyblock.luckyevents.WolfArmy;
import fr.paladium.palamod.modules.luckyblock.luckyevents.WolfEnemy;
import fr.paladium.palamod.modules.luckyblock.luckyevents.WowBG;
import fr.paladium.palamod.modules.luckyblock.luckyevents.ZombieHero;
import fr.paladium.palamod.modules.luckyblock.luckyevents.Zoo;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.Alchi;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.Cauchemardesque;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.Cosmetico;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.EasyCash;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.GogoGrip;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.GoldenOffer;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.KillerDauphin;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.LuckyLuke;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.MissileSA;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.NiGuemeu;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.Partoutladium;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.PetitChien;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.Sherpa;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.Solitaire;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.SonicBoots;
import fr.paladium.palamod.modules.luckyblock.luckyevents.black.Tchu;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.AteTooMuch;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.BlockGift;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.ChocolateMilk;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.ChristmasMockup;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.ChristmasStone;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.ChristmasVillage;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.ConsumerCredit;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.DrankTooMuch;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.ExtremeCold;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.ForestKing;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.FreezeEvent;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.NoescrocFather;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.Present;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.PresentBag;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.PrivateChristmas;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.Sleigh;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.SnowArmy;
import fr.paladium.palamod.modules.luckyblock.luckyevents.christmas.WishCard;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.AgneauPascal;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.CadeauPasPiege;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.CadeauPiege;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.ChasseOeufs;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.FaitFlipper;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.Hackerman;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.LapinGarou;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.OeufDur;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.Paquetole;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.Paquotille;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.PluieOeufs;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.PoissonAvril;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.PoissonsAvril;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.PouleOeufsOr;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.Tintamarre;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.TrucQuiCloche;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.VraiLapin;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.rework.AprilChestEvent;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.rework.NotAnAprilFoolEvent;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.rework.SoftLikeALambEvent;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.Bouh;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.CapeOuPasCape;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.CaseDepart;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.Citrouille;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.FullLegendary;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.HappyHalloween;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.HellYeah;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.InfinityPie;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.JackLantern;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.SixTrouilles;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.SorcererApprentice;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.SoupeDeCitrouille;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.SpiderSteve;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.SpookyAmulet;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.SpookySpook;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.TrickOrTreat;
import fr.paladium.palamod.modules.luckyblock.luckyevents.halloween.ZombieExit;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.AscenseurEmotionnel;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.BattleDanse;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.CinemaMuet;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.CoffreJuin;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.DisqueOr;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.EnfantTrompette;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.EnflammeDancefloor;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.HeavyMetal;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.MaladiePouetPouet;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.MusiqueQuiFaitPeur;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.PlusBatterie;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.Quack;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.ReveilleToi;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.SuisBip;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.SuperDanseur;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.TestRythme;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.VirusDance;
import fr.paladium.palamod.modules.luckyblock.luckyevents.june.VraiJukeboxVraieMusique;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.AffaireEstDansLeSac;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.AucuneAvancee;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.CageDoree;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.ChampDeFleur;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.CoffreMai;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.Creepy;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.FaireBoom;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.FeteTravail;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.ForceAvecToi;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.HommeInvisible;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.MLGPro;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.MeilleurAmi;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.OhLaVache;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.OnAimeOnNeComptePas;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.RepasDeRoi;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.TestForce;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.VaTeLaver;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.VieEnRose;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.VieilleHistoire;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.ArmorBakeEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.AugustChestEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.BeachVolleyEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.BlazingSunEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.DontMakeMessEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.LittleIceCreamEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.MoneyPoolEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.MosquitoEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.PackYourBagsEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.PowerfulFanEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.QuicksandEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SandCastleLifeEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.StayHydratedEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.StormEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SummerBodyDilemmaEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SummerHolidaysEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SummerSalesEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SummerWinterEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SunburnEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.BottleSeaEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.EyePatchEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.FeedTheFishEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.FelonEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.FreshWaterSailorEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.GoodSailorEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.HackerEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.LandInSightEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.MusicPartyEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.NoPowderTalkEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.ParrotEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.PhantomBoatEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.PipeAndWoodenLegEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.PirateKingTreasureEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.PowderTalkEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.RedCrossEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.ScorbutEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.TalkLikePirateEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.TreasureChestJulyEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.VigilanceDutyEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.AsteroidRainEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.AstronautTrainingEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.BeyondTheStarsEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.BlackHoleEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.EncounterThirdKindEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.HeadInStarsEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.InTheMoonEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.LikeARocketEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.LittleGreenMenEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.MarchChestEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.NoOxygenEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.ShootingStarEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.SpatialNoiseEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.VitalSpaceEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.WellCalibratedEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.ExplosiveInventoryEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.LittleAlchemistEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.ProfessionalJumperEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.SandStormEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.CareerEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.DevilsAdvocateEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.DreamJobEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.EnglishTestEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.ExploreJobOpeningsEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.FinalBossEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.FinallyABonusEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.GoodFortuneEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.GoodPayEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.HabitMakesMonkEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.HolidayPayEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.IsDreamJobEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.LotExperienceEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.PracticeMakesPerfectEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.ProfessionalSecrecyEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.RealButcherShopEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.RealFinalBossEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.SandmanEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.SeptemberChestEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.TimeIsMoneyEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.WorkIsHealthEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.minecraft.init.Blocks;

@DoNotRename
public enum LuckyEvents {
  COW_PLAYER((ALuckyEvent)new CowPlayer()),
  MINERAL_RUSH((ALuckyEvent)new MineralRush()),
  BODY_GUARD((ALuckyEvent)new BodyGuard()),
  FISH_BOWL((ALuckyEvent)new FishBowl()),
  GROS_RELOU((ALuckyEvent)new GrosRelou()),
  PIGGY_RODEO((ALuckyEvent)new PiggyRodeo()),
  FAKE_POWERED_CREEPER((ALuckyEvent)new FakePoweredCreeper()),
  MINERAL_SHOWER((ALuckyEvent)new MineralShower()),
  HOLE((ALuckyEvent)new Hole()),
  DIAMOND_BEACON((ALuckyEvent)new Beacon(Blocks.field_150484_ah, "Diamond Beacon", 9, 200, "diamond_beacon")),
  AMETHYST_BEACON((ALuckyEvent)new Beacon(BlocksRegister.BLOCK_AMETHYST, "Amethyst Beacon", 10, 400, "amethyst_beacon")),
  TITANE_BEACON((ALuckyEvent)new Beacon(BlocksRegister.BLOCK_TITANE, "Titane Beacon", 11, 800, "titane_beacon")),
  PALADIUM_BEACON((ALuckyEvent)new Beacon(BlocksRegister.BLOCK_PALADIUM, "Paladium Beacon", 12, 1200, "paladium_beacon")),
  ENDIUM_BEACON((ALuckyEvent)new Beacon(Blocks.field_150368_y, "Endium Beacon", 13, 400, "endium_beacon")),
  FIRE_MAN((ALuckyEvent)new FireMan()),
  PRINCESS((ALuckyEvent)new Princess()),
  PALA_TOWER((ALuckyEvent)new PalaTower()),
  BOOM_MOBS((ALuckyEvent)new BoomMobs()),
  WALK_IN_MUSIC((ALuckyEvent)new WalkInMusic()),
  BASE_DECO((ALuckyEvent)new BaseDeco()),
  FAST_LEARNER((ALuckyEvent)new FastLearner()),
  MEGA_FAST_LEARNER((ALuckyEvent)new MegaFastLearner()),
  EXPALAOSION((ALuckyEvent)new Expalaosion()),
  LUCKY((ALuckyEvent)new Lucky()),
  SPAWNER((ALuckyEvent)new Spawner()),
  LEGENDARY_STONE((ALuckyEvent)new LegendaryStone()),
  EXPLOSIF((ALuckyEvent)new Explosif()),
  CABALLO((ALuckyEvent)new Caballo()),
  SUPER_MAN((ALuckyEvent)new SuperMan()),
  PALA_RELOU((ALuckyEvent)new PalaRelou()),
  CAMOUFLAGE((ALuckyEvent)new Camouflage()),
  ENDIUM_RANK((ALuckyEvent)new DivinityRank()),
  WOW_BG((ALuckyEvent)new WowBG()),
  RODSHILD((ALuckyEvent)new Rodshild()),
  TETHANOS((ALuckyEvent)new Tethanos()),
  MEAGA_TETHANOS((ALuckyEvent)new MegaTethanos()),
  BADABOUM((ALuckyEvent)new BadaBoum()),
  NETHER((ALuckyEvent)new Nether()),
  DONT_BREAK((ALuckyEvent)new DontBreak()),
  GHAST((ALuckyEvent)new Ghast()),
  PUT_FIRE((ALuckyEvent)new PutFire()),
  ZOMBIE_HERO((ALuckyEvent)new ZombieHero()),
  TRAP((ALuckyEvent)new Trap()),
  GEYSER((ALuckyEvent)new Geyser()),
  ONE_SHOT((ALuckyEvent)new OneShot()),
  STAR_FISH((ALuckyEvent)new StarFish()),
  ARACHNO_TRAP((ALuckyEvent)new ArachnoTrap()),
  OBSI_TRAP((ALuckyEvent)new ObsiTrap()),
  BOOM((ALuckyEvent)new Boom()),
  WHO_ARE_YOU((ALuckyEvent)new WhoAreYou()),
  DIRT_IS_GOOD((ALuckyEvent)new DirtIsGood()),
  INSTANT_BREAK_UP((ALuckyEvent)new InstantBreakUp()),
  CLEAR_XP((ALuckyEvent)new ClearXP()),
  NAUSEA((ALuckyEvent)new Nausea()),
  SPAM_TP((ALuckyEvent)new SpamTp()),
  NERVOUS_BATMAN((ALuckyEvent)new NervousBatman()),
  SLIME_PAD((ALuckyEvent)new SlimePad()),
  ON_THE_MOON((ALuckyEvent)new OnTheMoon()),
  TREASURE_MAP_BAD((ALuckyEvent)new TreasureMapBad()),
  TREASURE_MAP_GOOD((ALuckyEvent)new TreasureMapGood()),
  PRICKLY_SHEEP((ALuckyEvent)new PricklySheep()),
  CONFINED((ALuckyEvent)new Confined()),
  BIG_DYNA((ALuckyEvent)new BigDyna()),
  REFLEX((ALuckyEvent)new Reflexe()),
  REVEAL((ALuckyEvent)new Reveal()),
  PALADIN((ALuckyEvent)new Paladin()),
  CRASH((ALuckyEvent)new Crash()),
  BOL((ALuckyEvent)new Bol()),
  MEGABOOM((ALuckyEvent)new MegaBoom()),
  TRIFORCE((ALuckyEvent)new Triforce()),
  FAKE_TNT((ALuckyEvent)new FakeTNT()),
  ANALYSTE((ALuckyEvent)new Analyste()),
  PYROBARBARE((ALuckyEvent)new Pyrobarbare()),
  WEIGHTED_BOOTS((ALuckyEvent)new WeightedBoots()),
  MY_NAME_IS_FUZEIII((ALuckyEvent)new MyNameIsFuzeIII()),
  JIMMY_HENDYX((ALuckyEvent)new JimmyHendyx()),
  TROMPETTE((ALuckyEvent)new Trompette()),
  GRAPPIN((ALuckyEvent)new Grappin()),
  RAINBOW_ARMOR((ALuckyEvent)new RainbowArmor()),
  LA_HAUT((ALuckyEvent)new LaHaut()),
  SPACE_FOOD((ALuckyEvent)new SpaceFood()),
  COLORFUL_LAMP((ALuckyEvent)new ColorfulLamp()),
  HUNTER_PLANT((ALuckyEvent)new HunterPlant()),
  PALACHAT((ALuckyEvent)new PalaChat()),
  MAGIC_POTION((ALuckyEvent)new MagicPotion()),
  ADMINS((ALuckyEvent)new Admins()),
  LASSO((ALuckyEvent)new Lasso()),
  JETPACK((ALuckyEvent)new Jetpack()),
  INVERSION((ALuckyEvent)new Inversion()),
  FACTION_LEAVE((ALuckyEvent)new FactionLeave()),
  ANGRY_WITHER((ALuckyEvent)new AngryWither()),
  LABYRINTHE((ALuckyEvent)new Labyrinthe()),
  CONSOLATION((ALuckyEvent)new Consolation()),
  ARMAGUEDON((ALuckyEvent)new Armaguedon()),
  TEAM_KILLER((ALuckyEvent)new TeamKiller()),
  SLIME_TOWER((ALuckyEvent)new SlimeTower()),
  SEUM((ALuckyEvent)new Seum()),
  ENDERCHEST((ALuckyEvent)new EnderChest()),
  REMOVE_MONEY((ALuckyEvent)new RemoveMoney()),
  ADD_MONEY((ALuckyEvent)new AddMoney()),
  WIFI((ALuckyEvent)new Wifi()),
  WITHER_HEAD((ALuckyEvent)new WitherHead()),
  COLORIAGE((ALuckyEvent)new Coloriage()),
  BIOME_PAINTER((ALuckyEvent)new BiomePainter()),
  RAYMAN((ALuckyEvent)new Rayman()),
  COMMANDO((ALuckyEvent)new Commando()),
  ROTATE((ALuckyEvent)new Rotate()),
  KAWAII_TNT((ALuckyEvent)new KawaiiTnt()),
  lESS_KAWAII_TNT((ALuckyEvent)new LessKawaiiTnt()),
  CHICKEN_SHOOT((ALuckyEvent)new ChickenShoot()),
  GHOST((ALuckyEvent)new Ghost()),
  PICASSO((ALuckyEvent)new Picasso()),
  NOT_TOO_FAR((ALuckyEvent)new NotTooFar(), new LuckyType[] { LuckyType.FINDIUM }),
  OVER_NINE_THOUSAND((ALuckyEvent)new OverNineThousand(), new LuckyType[] { LuckyType.FINDIUM }),
  DIRTY_INVENTORY((ALuckyEvent)new DirtyInventory(), new LuckyType[] { LuckyType.FINDIUM }),
  END((ALuckyEvent)new End(), new LuckyType[] { LuckyType.FINDIUM }),
  ITS_NOT_ALLOWED((ALuckyEvent)new ItsNotAllowed(), new LuckyType[] { LuckyType.FINDIUM }),
  NOTCH_IS_HERE((ALuckyEvent)new PalaIsHere(), new LuckyType[] { LuckyType.FINDIUM }),
  CHAOS_PROFESSOR((ALuckyEvent)new ChaosProfessor(), new LuckyType[] { LuckyType.FINDIUM }),
  TO_DOUM_DOUM((ALuckyEvent)new Todoumdoum(), new LuckyType[] { LuckyType.FINDIUM }),
  PIG_HELMET((ALuckyEvent)new PigHelmet(), new LuckyType[] { LuckyType.FINDIUM }),
  PIG_CHESTPLATE((ALuckyEvent)new PigChestplate(), new LuckyType[] { LuckyType.FINDIUM }),
  PIG_LEGGINGS((ALuckyEvent)new PigLeggings(), new LuckyType[] { LuckyType.FINDIUM }),
  PIG_BOOTS((ALuckyEvent)new PigBoots(), new LuckyType[] { LuckyType.FINDIUM }),
  WOLF_ALLY((ALuckyEvent)new WolfAlly(), new LuckyType[] { LuckyType.FINDIUM }),
  WOLF_ENEMY((ALuckyEvent)new WolfEnemy(), new LuckyType[] { LuckyType.FINDIUM }),
  FLASHMCQUEEN((ALuckyEvent)new FlashMcQueen(), new LuckyType[] { LuckyType.FINDIUM }),
  INJOUABLE((ALuckyEvent)new Injouable(), new LuckyType[] { LuckyType.FINDIUM }),
  STAYATHOME((ALuckyEvent)new StayAtHome(), new LuckyType[] { LuckyType.FINDIUM }),
  ITSHIGH((ALuckyEvent)new ItsHigh(), new LuckyType[] { LuckyType.FINDIUM }),
  FUZECOORDINATES((ALuckyEvent)new FuzeCoordinates(), new LuckyType[] { LuckyType.FINDIUM }),
  MOBTOWER((ALuckyEvent)new MobTower(), new LuckyType[] { LuckyType.FINDIUM }),
  POULPOGUN((ALuckyEvent)new PoulpoGun(), new LuckyType[] { LuckyType.FINDIUM }),
  BOUBAM((ALuckyEvent)new BouBam(), new LuckyType[] { LuckyType.FINDIUM }),
  RANTAMPLAN((ALuckyEvent)new Rantamplan(), new LuckyType[] { LuckyType.FINDIUM }),
  HEADONSHOULDERS((ALuckyEvent)new HeadOnShoulders(), new LuckyType[] { LuckyType.FINDIUM }),
  DUPLICATA((ALuckyEvent)new Duplicata(), new LuckyType[] { LuckyType.FINDIUM }),
  STEVE_JOBS((ALuckyEvent)new SteveJobs(), new LuckyType[] { LuckyType.FINDIUM }),
  NIGHT_VISION((ALuckyEvent)new NightVision(), new LuckyType[] { LuckyType.FINDIUM }),
  ENDIUM_BLOCK((ALuckyEvent)new EndiumBlock(), new LuckyType[] { LuckyType.FINDIUM }),
  METER((ALuckyEvent)new Meter(), new LuckyType[] { LuckyType.FINDIUM }),
  ELEPHANT((ALuckyEvent)new Elephant(), new LuckyType[] { LuckyType.FINDIUM }),
  INVISIBLE_HUMAN((ALuckyEvent)new InvisibleHuman(), new LuckyType[] { LuckyType.FINDIUM }),
  PALARELOU_GREEN((ALuckyEvent)new PalaRelouGreen(), new LuckyType[] { LuckyType.FINDIUM }),
  WHATDIDYOUEAT((ALuckyEvent)new WhatDidYouEat(), new LuckyType[] { LuckyType.FINDIUM }),
  DONT_DRINK_THAT((ALuckyEvent)new DontDrinkThat(), new LuckyType[] { LuckyType.FINDIUM }),
  DONT_DRINK_THAT_BAD((ALuckyEvent)new DontDrinkThatBad(), new LuckyType[] { LuckyType.FINDIUM }),
  ENIGME((ALuckyEvent)new Enigme(), new LuckyType[] { LuckyType.FINDIUM }),
  FREEXP((ALuckyEvent)new FreeXP(), new LuckyType[] { LuckyType.FINDIUM }),
  FREEMOREXP((ALuckyEvent)new FreeMoreXP(), new LuckyType[] { LuckyType.FINDIUM }),
  HAVEFUN((ALuckyEvent)new HaveFun(), new LuckyType[] { LuckyType.FINDIUM }),
  PERECASTOR((ALuckyEvent)new PereCastor(), new LuckyType[] { LuckyType.FINDIUM }),
  ABANDONEDBASE((ALuckyEvent)new AbandonedBase(), new LuckyType[] { LuckyType.FINDIUM }),
  CHEWEDWORK((ALuckyEvent)new ChewedWork(), new LuckyType[] { LuckyType.FINDIUM }),
  ITSHOT((ALuckyEvent)new ItsHot(), new LuckyType[] { LuckyType.FINDIUM }),
  ANVILHEAD((ALuckyEvent)new AnvilHead(), new LuckyType[] { LuckyType.FINDIUM }),
  ZOO((ALuckyEvent)new Zoo(), new LuckyType[] { LuckyType.FINDIUM }),
  NETHER_ENABLED((ALuckyEvent)new NetherEnabled(), new LuckyType[] { LuckyType.FINDIUM }),
  BREAKEARS((ALuckyEvent)new BreakEars(), new LuckyType[] { LuckyType.FINDIUM }),
  INVOCATOR((ALuckyEvent)new Invocator(), new LuckyType[] { LuckyType.FINDIUM }),
  CREEPESQUE((ALuckyEvent)new Creepesque(), new LuckyType[] { LuckyType.FINDIUM }),
  SMOKEDSALMON((ALuckyEvent)new SmokedSalmon(), new LuckyType[] { LuckyType.FINDIUM }),
  MINEDTERRAIN((ALuckyEvent)new MinedTerrain(), new LuckyType[] { LuckyType.FINDIUM }),
  GOLEMUPGRADE((ALuckyEvent)new GolemUpgrade(), new LuckyType[] { LuckyType.FINDIUM }),
  ILOVECATS((ALuckyEvent)new ILoveCats(), new LuckyType[] { LuckyType.FINDIUM }),
  PETROCK((ALuckyEvent)new PetRock(), new LuckyType[] { LuckyType.FINDIUM }),
  NOOBTRAP((ALuckyEvent)new NoobTrap(), new LuckyType[] { LuckyType.FINDIUM }),
  ILOVEYOUEARLY((ALuckyEvent)new ILoveYouEarly(), new LuckyType[] { LuckyType.FINDIUM }),
  ORANGEORBLUE((ALuckyEvent)new OrangeOrBlue(), new LuckyType[] { LuckyType.FINDIUM }),
  GENERALDRUDGERY((ALuckyEvent)new GeneralDrudgery(), new LuckyType[] { LuckyType.FINDIUM }),
  HOWDIDYOUGETTHAT((ALuckyEvent)new HowDidYouGetThat(), new LuckyType[] { LuckyType.FINDIUM }),
  COFFREFORT((ALuckyEvent)new CoffreFort(), new LuckyType[] { LuckyType.FINDIUM }),
  MEGACOFFREFORT((ALuckyEvent)new MegaCoffreFort(), new LuckyType[] { LuckyType.FINDIUM }),
  ULTRACOFFREFORT((ALuckyEvent)new UltraCoffreFort(), new LuckyType[] { LuckyType.FINDIUM }),
  GOLEMCRASSEUX((ALuckyEvent)new GolemCrasseux(), new LuckyType[] { LuckyType.FINDIUM }),
  JOHNTHERIPPER((ALuckyEvent)new JohnTheRipper(), new LuckyType[] { LuckyType.FINDIUM }),
  CAVEHELMET((ALuckyEvent)new CaveHelmet(), new LuckyType[] { LuckyType.FINDIUM }),
  REMOVEDFROMGAME((ALuckyEvent)new RemovedFromGame(), new LuckyType[] { LuckyType.FINDIUM }),
  PALASTRANGE((ALuckyEvent)new PaladiumStrange(), new LuckyType[] { LuckyType.FINDIUM }),
  TIMEMACHINE((ALuckyEvent)new TimeMachine(), new LuckyType[] { LuckyType.FINDIUM }),
  SPAMTPAHERE((ALuckyEvent)new SpamTpaHere(), new LuckyType[] { LuckyType.FINDIUM }),
  DOGARMY((ALuckyEvent)new WolfArmy(), new LuckyType[] { LuckyType.FINDIUM }),
  BOUSEUL((ALuckyEvent)new Bouseul(), new LuckyType[] { LuckyType.FINDIUM }),
  COUCOU((ALuckyEvent)new Coucou(), new LuckyType[] { LuckyType.FINDIUM }),
  FAKEMONEY((ALuckyEvent)new FakeMoney(), new LuckyType[] { LuckyType.FINDIUM }),
  HIDEHEAD((ALuckyEvent)new HideHead(), new LuckyType[] { LuckyType.FINDIUM }),
  PEACENLOVE((ALuckyEvent)new PeaceNLove(), new LuckyType[] { LuckyType.FINDIUM }),
  SONIC((ALuckyEvent)new Sonic(), new LuckyType[] { LuckyType.FINDIUM }),
  STORMLIGHTING((ALuckyEvent)new StormLighting(), new LuckyType[] { LuckyType.FINDIUM }),
  DONTTELLANYONE((ALuckyEvent)new DontTellAnyone(), new LuckyType[] { LuckyType.FINDIUM }),
  DONTMOVE((ALuckyEvent)new DontMove(), new LuckyType[] { LuckyType.FINDIUM }),
  TELEPORTYONE((ALuckyEvent)new TeleportYOne(), new LuckyType[] { LuckyType.FINDIUM }),
  BALACLAVA((ALuckyEvent)new Balaclava(), new LuckyType[] { LuckyType.FINDIUM }),
  ONEMILLIONPLAYER((ALuckyEvent)new OneMillionPlayer(), new LuckyType[] { LuckyType.FINDIUM }),
  WETFIRECRACKER((ALuckyEvent)new WetFirecracker(), new LuckyType[] { LuckyType.FINDIUM }),
  CATDETECTOR((ALuckyEvent)new CatDetector(), new LuckyType[] { LuckyType.FINDIUM }),
  EYEBATTLE((ALuckyEvent)new EyeBattle(), new LuckyType[] { LuckyType.FINDIUM }),
  STALACTITES((ALuckyEvent)new Stalactites(), new LuckyType[] { LuckyType.FINDIUM }),
  ROULETTE((ALuckyEvent)new Roulette(), new LuckyType[] { LuckyType.FINDIUM }),
  FROZENGROUND((ALuckyEvent)new FrozenGround(), new LuckyType[] { LuckyType.FINDIUM }),
  SLEEPINGBAG((ALuckyEvent)new SleepingBag(), new LuckyType[] { LuckyType.FINDIUM }),
  VOYANT((ALuckyEvent)new Voyant(), new LuckyType[] { LuckyType.FINDIUM }),
  SODA((ALuckyEvent)new Soda(), new LuckyType[] { LuckyType.FINDIUM }),
  MAGNETIC((ALuckyEvent)new Magnetic(), new LuckyType[] { LuckyType.FINDIUM }),
  FUZEPERSONAL((ALuckyEvent)new FuzePersonal(), new LuckyType[] { LuckyType.FINDIUM }),
  GRANDMAMIX((ALuckyEvent)new GrandmaMix(), new LuckyType[] { LuckyType.FINDIUM }),
  TEAMTREE((ALuckyEvent)new TeamTree(), new LuckyType[] { LuckyType.FINDIUM }),
  CALCIUM((ALuckyEvent)new Calcium(), new LuckyType[] { LuckyType.FINDIUM }),
  AIRMIN((ALuckyEvent)new AirMin(), new LuckyType[] { LuckyType.FINDIUM }),
  GODPICKAXEMAX((ALuckyEvent)new GodPickaxeMax(), new LuckyType[] { LuckyType.FINDIUM }),
  SQUIDGAME((ALuckyEvent)new SquidGameHelmet(), new LuckyType[] { LuckyType.FINDIUM }),
  BANANA((ALuckyEvent)new Banana(), new LuckyType[] { LuckyType.FINDIUM }),
  FAKESTUFFENDIUM((ALuckyEvent)new FakeStuffEndium(), new LuckyType[] { LuckyType.FINDIUM }),
  TRYAGAIN((ALuckyEvent)new TryAgain(), new LuckyType[] { LuckyType.FINDIUM }),
  POULAY((ALuckyEvent)new Poulay(), new LuckyType[] { LuckyType.FINDIUM }),
  FLOU((ALuckyEvent)new Flou(), new LuckyType[] { LuckyType.FINDIUM }),
  VILLANUL((ALuckyEvent)new VillaNul(), new LuckyType[] { LuckyType.FINDIUM }),
  BEE((ALuckyEvent)new Bee(), new LuckyType[] { LuckyType.FINDIUM }),
  BLINDNESS((ALuckyEvent)new Blindness(), new LuckyType[] { LuckyType.FINDIUM }),
  ALLINONE((ALuckyEvent)new AllInOne(), new LuckyType[] { LuckyType.FINDIUM }),
  PALAFAKEORE((ALuckyEvent)new PalaFakeOre(), new LuckyType[] { LuckyType.FINDIUM }),
  VIEWTWOPERSON((ALuckyEvent)new CancelView(), new LuckyType[] { LuckyType.FINDIUM }),
  HAPPYNEWYEAR((ALuckyEvent)new HappyNewYear(), new LuckyType[] { LuckyType.FINDIUM }),
  GOODBUTNOTTOOMUCH((ALuckyEvent)new GoodButNotTooMuch(), new LuckyType[] { LuckyType.FINDIUM }),
  TRAPSTRING((ALuckyEvent)new TrapString(), new LuckyType[] { LuckyType.FINDIUM }),
  MARIOMUSHRROM((ALuckyEvent)new MarioMushroom(), new LuckyType[] { LuckyType.FINDIUM }),
  STATSTONE((ALuckyEvent)new StatsStone(), new LuckyType[] { LuckyType.FINDIUM }),
  FUZESHOP((ALuckyEvent)new FuzeShop(), new LuckyType[] { LuckyType.FINDIUM }),
  BOATFURNACE((ALuckyEvent)new BoatFurnace(), new LuckyType[] { LuckyType.FINDIUM }),
  TEXTURENOTFOUND((ALuckyEvent)new TextureNotFound(), new LuckyType[] { LuckyType.FINDIUM }),
  DOUBLEXP((ALuckyEvent)new DoubleXP(), new LuckyType[] { LuckyType.FINDIUM }),
  DIVISEXP((ALuckyEvent)new DiviseXP(), new LuckyType[] { LuckyType.FINDIUM }),
  LAG((ALuckyEvent)new Lag(), new LuckyType[] { LuckyType.FINDIUM }),
  JACKPOT((ALuckyEvent)new Jackpot(), new LuckyType[] { LuckyType.FINDIUM }),
  READYFOREVERYTHING((ALuckyEvent)new ReadyForEverything(), new LuckyType[] { LuckyType.FINDIUM }),
  UNRANK((ALuckyEvent)new UnRank(), new LuckyType[] { LuckyType.FINDIUM }),
  CHATDETECTOR((ALuckyEvent)new ChatDetector(), new LuckyType[] { LuckyType.FINDIUM }),
  EXPLOSE((ALuckyEvent)new Explose(), new LuckyType[] { LuckyType.FINDIUM }),
  TOMBE((ALuckyEvent)new Tombe(), new LuckyType[] { LuckyType.FINDIUM }),
  LEBRON_JAMES((ALuckyEvent)new LebronJames(), new LuckyType[] { LuckyType.FINDIUM }),
  KOBE_BRYAN((ALuckyEvent)new KobeBryan(), new LuckyType[] { LuckyType.FINDIUM }),
  BAD_FACE((ALuckyEvent)new BadFace(), new LuckyType[] { LuckyType.FINDIUM }),
  SORCERER((ALuckyEvent)new SorcererApprentice(), new LuckyType[] { LuckyType.HALLOWEEN }),
  BOUH((ALuckyEvent)new Bouh(), new LuckyType[] { LuckyType.HALLOWEEN }),
  CAPEOUPASCAPE((ALuckyEvent)new CapeOuPasCape(), new LuckyType[] { LuckyType.HALLOWEEN }),
  CASEDEPART((ALuckyEvent)new CaseDepart(), new LuckyType[] { LuckyType.HALLOWEEN }),
  CITROUILLE((ALuckyEvent)new Citrouille(), new LuckyType[] { LuckyType.HALLOWEEN }),
  FULLLEGENDARY((ALuckyEvent)new FullLegendary(), new LuckyType[] { LuckyType.HALLOWEEN }),
  HAPPYHALLOWEEN((ALuckyEvent)new HappyHalloween(), new LuckyType[] { LuckyType.HALLOWEEN }),
  HELLYEAH((ALuckyEvent)new HellYeah(), new LuckyType[] { LuckyType.HALLOWEEN }),
  ITSNOTYOU((ALuckyEvent)new ZombieExit(), new LuckyType[] { LuckyType.HALLOWEEN }),
  SIXTROUILLES((ALuckyEvent)new SixTrouilles(), new LuckyType[] { LuckyType.HALLOWEEN }),
  SOUPEDECITROUILLE((ALuckyEvent)new SoupeDeCitrouille(), new LuckyType[] { LuckyType.HALLOWEEN }),
  SPIDERSTEVE((ALuckyEvent)new SpiderSteve(), new LuckyType[] { LuckyType.HALLOWEEN }),
  SPOOKYSPOOK((ALuckyEvent)new SpookySpook(), new LuckyType[] { LuckyType.HALLOWEEN }),
  TRICKORTREAT((ALuckyEvent)new TrickOrTreat(), new LuckyType[] { LuckyType.HALLOWEEN }),
  SMALL_CANDIES((ALuckyEvent)new JackLantern(), new LuckyType[] { LuckyType.HALLOWEEN }),
  BIG_CANDIES((ALuckyEvent)new InfinityPie(), new LuckyType[] { LuckyType.HALLOWEEN }),
  SPOOKY_AMULET((ALuckyEvent)new SpookyAmulet(), new LuckyType[] { LuckyType.HALLOWEEN }),
  CHRISTMAS_STONE((ALuckyEvent)new ChristmasStone(), new LuckyType[] { LuckyType.CHRISTMAS }),
  FREEZE((ALuckyEvent)new FreezeEvent(), new LuckyType[] { LuckyType.CHRISTMAS }),
  SLEIGH((ALuckyEvent)new Sleigh(), new LuckyType[] { LuckyType.CHRISTMAS }),
  PRESENT_BAG((ALuckyEvent)new PresentBag(), new LuckyType[] { LuckyType.CHRISTMAS }),
  PRESENT((ALuckyEvent)new Present(), new LuckyType[] { LuckyType.CHRISTMAS }),
  CHOCOLATE_MILK((ALuckyEvent)new ChocolateMilk(), new LuckyType[] { LuckyType.CHRISTMAS }),
  FOREST_KING((ALuckyEvent)new ForestKing(), new LuckyType[] { LuckyType.CHRISTMAS }),
  ATE_TOO_MUCH((ALuckyEvent)new AteTooMuch(), new LuckyType[] { LuckyType.CHRISTMAS }),
  DRANK_TOO_MUCH((ALuckyEvent)new DrankTooMuch(), new LuckyType[] { LuckyType.CHRISTMAS }),
  CONSUMER_CREDIT((ALuckyEvent)new ConsumerCredit(), new LuckyType[] { LuckyType.CHRISTMAS }),
  PRIVATE_CHRISTMAS((ALuckyEvent)new PrivateChristmas(), new LuckyType[] { LuckyType.CHRISTMAS }),
  CHRISTMAS_VILLAGE((ALuckyEvent)new ChristmasVillage(), new LuckyType[] { LuckyType.CHRISTMAS }),
  EXTREME_COLD((ALuckyEvent)new ExtremeCold(), new LuckyType[] { LuckyType.CHRISTMAS }),
  WISH_CARD((ALuckyEvent)new WishCard(), new LuckyType[] { LuckyType.CHRISTMAS }),
  CHRISTMAS_GIFT((ALuckyEvent)new BlockGift(), new LuckyType[] { LuckyType.CHRISTMAS }),
  CHRISTMAS_MOCKUP((ALuckyEvent)new ChristmasMockup(), new LuckyType[] { LuckyType.CHRISTMAS }),
  SNOW_ARMY((ALuckyEvent)new SnowArmy(), new LuckyType[] { LuckyType.CHRISTMAS }),
  NOESCROC_FATHER((ALuckyEvent)new NoescrocFather(), new LuckyType[] { LuckyType.CHRISTMAS }),
  KILLER_DAUPHIN((ALuckyEvent)new KillerDauphin(), new LuckyType[] { LuckyType.BLACK }),
  COSMETICO((ALuckyEvent)new Cosmetico(), new LuckyType[] { LuckyType.BLACK }),
  ALCHI((ALuckyEvent)new Alchi(), new LuckyType[] { LuckyType.BLACK }),
  EASY_CASH((ALuckyEvent)new EasyCash(), new LuckyType[] { LuckyType.BLACK }),
  SHERPA((ALuckyEvent)new Sherpa(), new LuckyType[] { LuckyType.BLACK }),
  SOLITAIRE((ALuckyEvent)new Solitaire(), new LuckyType[] { LuckyType.BLACK }),
  SONIC_BOOTS((ALuckyEvent)new SonicBoots(), new LuckyType[] { LuckyType.BLACK }),
  PARTOUTLADIUM((ALuckyEvent)new Partoutladium(), new LuckyType[] { LuckyType.BLACK }),
  NI_GUEMEU((ALuckyEvent)new NiGuemeu(), new LuckyType[] { LuckyType.BLACK }),
  CAUCHEMARDESQUE((ALuckyEvent)new Cauchemardesque(), new LuckyType[] { LuckyType.BLACK }),
  GOLDEN_OFFER((ALuckyEvent)new GoldenOffer(), new LuckyType[] { LuckyType.BLACK }),
  PETIT_CHIEN((ALuckyEvent)new PetitChien(), new LuckyType[] { LuckyType.BLACK }),
  MISSILE_SA((ALuckyEvent)new MissileSA(), new LuckyType[] { LuckyType.BLACK }),
  GOGO_GRIP((ALuckyEvent)new GogoGrip(), new LuckyType[] { LuckyType.BLACK }),
  TCHU((ALuckyEvent)new Tchu(), new LuckyType[] { LuckyType.BLACK }),
  LUCKYLUKE((ALuckyEvent)new LuckyLuke(), new LuckyType[] { LuckyType.BLACK }),
  VRAI_LAPIN((ALuckyEvent)new VraiLapin(), new LuckyType[] { LuckyType.EASTER }),
  AGNEAU_PASCAL((ALuckyEvent)new AgneauPascal(), new LuckyType[] { LuckyType.EASTER }),
  PAQUOTILLE((ALuckyEvent)new Paquotille(), new LuckyType[] { LuckyType.EASTER }),
  TINTAMARRE((ALuckyEvent)new Tintamarre(), new LuckyType[] { LuckyType.EASTER }),
  TRUC_QUI_CLOCHE((ALuckyEvent)new TrucQuiCloche(), new LuckyType[] { LuckyType.EASTER }),
  OEUF_DUR((ALuckyEvent)new OeufDur(), new LuckyType[] { LuckyType.EASTER }),
  LAPIN_GAROU((ALuckyEvent)new LapinGarou(), new LuckyType[] { LuckyType.EASTER }),
  POISSON_AVRIL((ALuckyEvent)new PoissonAvril(), new LuckyType[] { LuckyType.EASTER }),
  HACKERMAN((ALuckyEvent)new Hackerman(), new LuckyType[] { LuckyType.EASTER }),
  FAIT_FLIPPER((ALuckyEvent)new FaitFlipper(), new LuckyType[] { LuckyType.EASTER }),
  CHASSE_OEUF((ALuckyEvent)new ChasseOeufs(), new LuckyType[] { LuckyType.EASTER }),
  PAQUETOLE((ALuckyEvent)new Paquetole(), new LuckyType[] { LuckyType.EASTER }),
  PLUIE_OEUFS((ALuckyEvent)new PluieOeufs(), new LuckyType[] { LuckyType.EASTER }),
  POISSONS_AVRIL((ALuckyEvent)new PoissonsAvril(), new LuckyType[] { LuckyType.EASTER }),
  CADEAU_PIEGE((ALuckyEvent)new CadeauPiege(), new LuckyType[] { LuckyType.EASTER }),
  CADEAU_PAS_PIEGE((ALuckyEvent)new CadeauPasPiege(), new LuckyType[] { LuckyType.EASTER }),
  POULE_OEUF_OR((ALuckyEvent)new PouleOeufsOr(), new LuckyType[] { LuckyType.EASTER }),
  FETE_TRAVAIL((ALuckyEvent)new FeteTravail(), new LuckyType[] { LuckyType.MAY }),
  REPAS_DE_ROI((ALuckyEvent)new RepasDeRoi(), new LuckyType[] { LuckyType.MAY }),
  FAIRE_BOOM((ALuckyEvent)new FaireBoom(), new LuckyType[] { LuckyType.MAY }),
  TEST_FORCE((ALuckyEvent)new TestForce(), new LuckyType[] { LuckyType.MAY }),
  VIE_EN_ROSE((ALuckyEvent)new VieEnRose(), new LuckyType[] { LuckyType.MAY }),
  MLG_PRO((ALuckyEvent)new MLGPro(), new LuckyType[] { LuckyType.MAY }),
  CAGE_DOREE((ALuckyEvent)new CageDoree(), new LuckyType[] { LuckyType.MAY }),
  ON_AIME_ON_COMPTE_PAS((ALuckyEvent)new OnAimeOnNeComptePas(), new LuckyType[] { LuckyType.MAY }),
  AUCUNE_AVANCEE((ALuckyEvent)new AucuneAvancee(), new LuckyType[] { LuckyType.MAY }),
  COFFRE_MAI((ALuckyEvent)new CoffreMai(), new LuckyType[] { LuckyType.MAY }),
  VIEILLE_HISTOIRE((ALuckyEvent)new VieilleHistoire(), new LuckyType[] { LuckyType.MAY }),
  MEILLEUR_AMI((ALuckyEvent)new MeilleurAmi(), new LuckyType[] { LuckyType.MAY }),
  VA_TE_LAVER((ALuckyEvent)new VaTeLaver(), new LuckyType[] { LuckyType.MAY }),
  FORCE_AVEC_TOI((ALuckyEvent)new ForceAvecToi(), new LuckyType[] { LuckyType.MAY }),
  CREEPY((ALuckyEvent)new Creepy(), new LuckyType[] { LuckyType.MAY }),
  COFFRE_JUIN((ALuckyEvent)new CoffreJuin(), new LuckyType[] { LuckyType.JUNE }),
  QUACK((ALuckyEvent)new Quack(), new LuckyType[] { LuckyType.JUNE }),
  CINEMA_MUET((ALuckyEvent)new CinemaMuet(), new LuckyType[] { LuckyType.JUNE }),
  SUPER_DANSEUR((ALuckyEvent)new SuperDanseur(), new LuckyType[] { LuckyType.JUNE }),
  PLUS_BATTERIE((ALuckyEvent)new PlusBatterie(), new LuckyType[] { LuckyType.JUNE }),
  HEAVY_METAL((ALuckyEvent)new HeavyMetal(), new LuckyType[] { LuckyType.JUNE }),
  DISQUE_OR((ALuckyEvent)new DisqueOr(), new LuckyType[] { LuckyType.JUNE }),
  ENFLAMME_DANCEFLOOR((ALuckyEvent)new EnflammeDancefloor(), new LuckyType[] { LuckyType.JUNE }),
  ENFANT_TROMPETTE((ALuckyEvent)new EnfantTrompette(), new LuckyType[] { LuckyType.JUNE }),
  MALADIE_POUET_POUET((ALuckyEvent)new MaladiePouetPouet(), new LuckyType[] { LuckyType.JUNE }),
  VIRUS_DANCE((ALuckyEvent)new VirusDance(), new LuckyType[] { LuckyType.JUNE }),
  SUIS_BIP((ALuckyEvent)new SuisBip(), new LuckyType[] { LuckyType.JUNE }),
  VRAI_JUKEBOX_VRAIE_MUSIQUE((ALuckyEvent)new VraiJukeboxVraieMusique(), new LuckyType[] { LuckyType.JUNE }),
  ASCENSEUR_EMOTIONNEL((ALuckyEvent)new AscenseurEmotionnel(), new LuckyType[] { LuckyType.JUNE }),
  MUSIQUE_QUI_FAIT_PEUR((ALuckyEvent)new MusiqueQuiFaitPeur(), new LuckyType[] { LuckyType.JUNE }),
  REVEILLE_TOI((ALuckyEvent)new ReveilleToi(), new LuckyType[] { LuckyType.JUNE }),
  BATTLE_DANCE((ALuckyEvent)new BattleDanse(), new LuckyType[] { LuckyType.JUNE }),
  TEST_RYTHME((ALuckyEvent)new TestRythme(), new LuckyType[] { LuckyType.JUNE }),
  BOTTLE_SEA((ALuckyEvent)new BottleSeaEvent(), new LuckyType[] { LuckyType.JULY }),
  EYE_PATCH((ALuckyEvent)new EyePatchEvent(), new LuckyType[] { LuckyType.JULY }),
  FEED_THE_FISH((ALuckyEvent)new FeedTheFishEvent(), new LuckyType[] { LuckyType.JULY }),
  FELON((ALuckyEvent)new FelonEvent(), new LuckyType[] { LuckyType.JULY }),
  FRESH_WATER_SAILOR((ALuckyEvent)new FreshWaterSailorEvent(), new LuckyType[] { LuckyType.JULY }),
  GOOD_SAILOR((ALuckyEvent)new GoodSailorEvent(), new LuckyType[] { LuckyType.JULY }),
  HACKER((ALuckyEvent)new HackerEvent(), new LuckyType[] { LuckyType.JULY }),
  LAND_IN_SIGHT((ALuckyEvent)new LandInSightEvent(), new LuckyType[] { LuckyType.JULY }),
  MUSIC_PARTY((ALuckyEvent)new MusicPartyEvent(), new LuckyType[] { LuckyType.JULY }),
  PARROT((ALuckyEvent)new ParrotEvent(), new LuckyType[] { LuckyType.JULY }),
  PHANTOM_BOAT((ALuckyEvent)new PhantomBoatEvent(), new LuckyType[] { LuckyType.JULY }),
  PIPE_AND_WOODEN_LEG((ALuckyEvent)new PipeAndWoodenLegEvent(), new LuckyType[] { LuckyType.JULY }),
  PIRATE_KING_TREASURE((ALuckyEvent)new PirateKingTreasureEvent(), new LuckyType[] { LuckyType.JULY }),
  POWDER_TALK((ALuckyEvent)new PowderTalkEvent(), new LuckyType[] { LuckyType.JULY }),
  NO_POWDER_TALK((ALuckyEvent)new NoPowderTalkEvent(), new LuckyType[] { LuckyType.JULY }),
  RED_CROSS((ALuckyEvent)new RedCrossEvent(), new LuckyType[] { LuckyType.JULY }),
  SCORBUT((ALuckyEvent)new ScorbutEvent(), new LuckyType[] { LuckyType.JULY }),
  TALK_LIKE_PIRATE((ALuckyEvent)new TalkLikePirateEvent(), new LuckyType[] { LuckyType.JULY }),
  TREASURE_CHEST_JULY((ALuckyEvent)new TreasureChestJulyEvent(), new LuckyType[] { LuckyType.JULY }),
  VIGILANCE_DUTY((ALuckyEvent)new VigilanceDutyEvent(), new LuckyType[] { LuckyType.JULY }),
  DONT_MAKE_MESS((ALuckyEvent)new DontMakeMessEvent(), new LuckyType[] { LuckyType.AUGUST }),
  SUMMER_BODY_DILEMMA((ALuckyEvent)new SummerBodyDilemmaEvent(), new LuckyType[] { LuckyType.AUGUST }),
  SUNBURN((ALuckyEvent)new SunburnEvent(), new LuckyType[] { LuckyType.AUGUST }),
  BLAZING_SUN((ALuckyEvent)new BlazingSunEvent(), new LuckyType[] { LuckyType.AUGUST }),
  BEACH_VOLLEY((ALuckyEvent)new BeachVolleyEvent(), new LuckyType[] { LuckyType.AUGUST }),
  SANDCASTLE((ALuckyEvent)new SandCastleLifeEvent(), new LuckyType[] { LuckyType.AUGUST }),
  MONEY_POOL((ALuckyEvent)new MoneyPoolEvent(), new LuckyType[] { LuckyType.AUGUST }),
  SUMMER_SALE((ALuckyEvent)new SummerSalesEvent(), new LuckyType[] { LuckyType.AUGUST }),
  SUMMER_HOLIDAYS((ALuckyEvent)new SummerHolidaysEvent(), new LuckyType[] { LuckyType.AUGUST }),
  PACK_YOUR_BAGS((ALuckyEvent)new PackYourBagsEvent(), new LuckyType[] { LuckyType.AUGUST }),
  LITTLE_ICE_CREAM((ALuckyEvent)new LittleIceCreamEvent(), new LuckyType[] { LuckyType.AUGUST }),
  MOSQUITO((ALuckyEvent)new MosquitoEvent(), new LuckyType[] { LuckyType.AUGUST }),
  ARMOR_BAKE((ALuckyEvent)new ArmorBakeEvent(), new LuckyType[] { LuckyType.AUGUST }),
  STORM((ALuckyEvent)new StormEvent(), new LuckyType[] { LuckyType.AUGUST }),
  AUGUST_CHEST((ALuckyEvent)new AugustChestEvent(), new LuckyType[] { LuckyType.AUGUST }),
  STAY_HYDRATED((ALuckyEvent)new StayHydratedEvent(), new LuckyType[] { LuckyType.AUGUST }),
  SUMMER_WINTER((ALuckyEvent)new SummerWinterEvent(), new LuckyType[] { LuckyType.AUGUST }),
  POWERFUL_FAN((ALuckyEvent)new PowerfulFanEvent(), new LuckyType[] { LuckyType.AUGUST }),
  QUICK_SAND((ALuckyEvent)new QuicksandEvent(), new LuckyType[] { LuckyType.AUGUST }),
  PROFESSIONAL_SECRECY((ALuckyEvent)new ProfessionalSecrecyEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  GOOD_PAY((ALuckyEvent)new GoodPayEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  TIME_IS_MONEY((ALuckyEvent)new TimeIsMoneyEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  REAL_FINAL_BOSS((ALuckyEvent)new RealFinalBossEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  CAREER((ALuckyEvent)new CareerEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  WORK_IS_HEALTH((ALuckyEvent)new WorkIsHealthEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  PRACTICE_MAKES_PERFECT((ALuckyEvent)new PracticeMakesPerfectEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  DEVILS_ADVOCATE((ALuckyEvent)new DevilsAdvocateEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  SANDMAN((ALuckyEvent)new SandmanEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  HABIT_MAKES_MONK((ALuckyEvent)new HabitMakesMonkEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  FINALLY_A_BONUS((ALuckyEvent)new FinallyABonusEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  HOLIDAY_PAY((ALuckyEvent)new HolidayPayEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  FINAL_BOSS((ALuckyEvent)new FinalBossEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  GOOD_FORTUNE((ALuckyEvent)new GoodFortuneEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  DREAM_JOB((ALuckyEvent)new DreamJobEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  IS_DREAM_JOB((ALuckyEvent)new IsDreamJobEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  EXPLORE_JOBS_OPENING((ALuckyEvent)new ExploreJobOpeningsEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  ENGLISH_TEST((ALuckyEvent)new EnglishTestEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  REAL_BUTCHER_SHOP((ALuckyEvent)new RealButcherShopEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  LOT_EXPERIENCE((ALuckyEvent)new LotExperienceEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  SEPTEMBER_CHEST((ALuckyEvent)new SeptemberChestEvent(), new LuckyType[] { LuckyType.SEPTEMBER }),
  ASTEROID_RAIN((ALuckyEvent)new AsteroidRainEvent(), new LuckyType[] { LuckyType.MARCH }),
  ASTRONAUT_TRAINING((ALuckyEvent)new AstronautTrainingEvent(), new LuckyType[] { LuckyType.MARCH }),
  BEYOND_THE_STARS((ALuckyEvent)new BeyondTheStarsEvent(), new LuckyType[] { LuckyType.MARCH }),
  BLACK_HOLE((ALuckyEvent)new BlackHoleEvent(), new LuckyType[] { LuckyType.MARCH }),
  ENCOUNTER_THIRD_KIND((ALuckyEvent)new EncounterThirdKindEvent(), new LuckyType[] { LuckyType.MARCH }),
  HEAD_IN_THE_STARS((ALuckyEvent)new HeadInStarsEvent(), new LuckyType[] { LuckyType.MARCH }),
  IN_THE_MOON((ALuckyEvent)new InTheMoonEvent(), new LuckyType[] { LuckyType.MARCH }),
  LIKE_A_ROCKET((ALuckyEvent)new LikeARocketEvent(), new LuckyType[] { LuckyType.MARCH }),
  LITTLE_GREEN_MEN((ALuckyEvent)new LittleGreenMenEvent(), new LuckyType[] { LuckyType.MARCH }),
  MARCH_CHEST((ALuckyEvent)new MarchChestEvent(), new LuckyType[] { LuckyType.MARCH }),
  NO_OXYGEN((ALuckyEvent)new NoOxygenEvent(), new LuckyType[] { LuckyType.MARCH }),
  SHOOTING_STAR((ALuckyEvent)new ShootingStarEvent(), new LuckyType[] { LuckyType.MARCH }),
  SPATIAL_NOISE((ALuckyEvent)new SpatialNoiseEvent(), new LuckyType[] { LuckyType.MARCH }),
  VITAL_SPACE((ALuckyEvent)new VitalSpaceEvent(), new LuckyType[] { LuckyType.MARCH }),
  WELL_CALIBRATED((ALuckyEvent)new WellCalibratedEvent(), new LuckyType[] { LuckyType.MARCH }),
  APRIL_CHEST((ALuckyEvent)new AprilChestEvent(), new LuckyType[] { LuckyType.EASTER }),
  NOT_AN_APRIL_FOOL((ALuckyEvent)new NotAnAprilFoolEvent(), new LuckyType[] { LuckyType.EASTER }),
  SOFT_LIKE_A_LAMB((ALuckyEvent)new SoftLikeALambEvent(), new LuckyType[] { LuckyType.EASTER }),
  OH_LA_VACHE((ALuckyEvent)new OhLaVache(), new LuckyType[] { LuckyType.MAY }),
  CHAMP_DE_FLEUR((ALuckyEvent)new ChampDeFleur(), new LuckyType[] { LuckyType.MAY }),
  AFFAIRE_EST_DANS_LE_SAC((ALuckyEvent)new AffaireEstDansLeSac(), new LuckyType[] { LuckyType.MAY }),
  HOMME_INVISIBLE((ALuckyEvent)new HommeInvisible(), new LuckyType[] { LuckyType.MAY }),
  NOVEMBER_BLACK_HOLE((ALuckyEvent)new BlackHoleEvent(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_WELL_CALIBRATED((ALuckyEvent)new WellCalibratedEvent(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_H4CK3R((ALuckyEvent)new Hackerman(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_CADEAU_PAS_PIEGE((ALuckyEvent)new CadeauPasPiege(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_WORK_PARTY((ALuckyEvent)new FeteTravail(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_VA_TE_LAVER((ALuckyEvent)new VaTeLaver(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_REVEIL_TOI((ALuckyEvent)new ReveilleToi(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_ENFLAMME_DANCEFLOOR((ALuckyEvent)new EnflammeDancefloor(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_TALK_LIKE_PIRATE((ALuckyEvent)new TalkLikePirateEvent(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_JOB_EXLORATION((ALuckyEvent)new ExploreJobOpeningsEvent(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_REAL_FINAL_BOSS((ALuckyEvent)new RealFinalBossEvent(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_EXPLOSIVE_INVENTORY((ALuckyEvent)new ExplosiveInventoryEvent(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_PROFESSIONAL_JUMPER((ALuckyEvent)new ProfessionalJumperEvent(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_SANDSTORM((ALuckyEvent)new SandStormEvent(), new LuckyType[] { LuckyType.NOVEMBER }),
  NOVEMBER_ALCHEMIST_KIT((ALuckyEvent)new LittleAlchemistEvent(), new LuckyType[] { LuckyType.NOVEMBER });
  
  static {
    values = values();
  }
  
  List<LuckyType> types = new ArrayList<>();
  
  private static final LuckyEvents[] values;
  
  private final ALuckyEvent event;
  
  public List<LuckyType> getTypes() {
    return this.types;
  }
  
  public ALuckyEvent getEvent() {
    return this.event;
  }
  
  LuckyEvents(ALuckyEvent event) {
    if (event == null)
      throw new IllegalArgumentException("ALuckyEvent cannot be null (check your instance)"); 
    this.event = event;
    this.types.add(LuckyType.PALADIUM);
    if (!event.isBad())
      this.types.add(LuckyType.ENDIUM); 
  }
  
  LuckyEvents(ALuckyEvent event, LuckyType... types) {
    if (event == null)
      throw new IllegalArgumentException("ALuckyEvent cannot be null (check your instance)"); 
    this.event = event;
    Collections.addAll(this.types, types);
  }
  
  public static int indexOf(LuckyEvents event) {
    for (int i = 0; i < values.length; i++) {
      if (values[i] == event)
        return i; 
    } 
    return 0;
  }
  
  public static LuckyEvents[] valuesByType(LuckyType type) {
    return (LuckyEvents[])Arrays.<LuckyEvents>asList(values()).stream().filter(e -> e.getTypes().contains(type))
      .toArray(size -> new LuckyEvents[size]);
  }
  
  public static LuckyEvents getRandomBadEvent() {
    LuckyEvents[] list = (LuckyEvents[])Arrays.<LuckyEvents>asList(values()).stream().filter(e -> e.event.isBad()).toArray(size -> new LuckyEvents[size]);
    int random = (new Random()).nextInt(list.length);
    return list[random];
  }
  
  public static List<ALuckyEvent> valuesEvents(LuckyType type) {
    List<ALuckyEvent> events = new ArrayList<>();
    for (LuckyEvents event : values) {
      event.getTypes().stream().filter(t -> (t == type)).forEach(t -> {
            if (!ConfigManager.hasKey("luckyblock", String.valueOf(event)) || !ConfigManager.getBoolean("luckyblock", String.valueOf(event)))
              events.add(event.event); 
          });
    } 
    return events;
  }
  
  public static LuckyEvents fromEvent(ALuckyEvent event) {
    for (int i = 0; i < values.length; i++) {
      if ((values[i]).event.equals(event))
        return values[i]; 
    } 
    return null;
  }
  
  public static LuckyEvents fromClass(Class<?> eventClass) {
    for (int i = 0; i < values.length; i++) {
      if (eventClass.equals((values[i]).event.getClass()))
        return values[i]; 
    } 
    return null;
  }
  
  public static LuckyEvents fromName(String name) {
    for (int i = 0; i < values.length; i++) {
      if (name.equals(values[i].name()))
        return values[i]; 
    } 
    return null;
  }
  
  public double getProbability(double totalWeight) {
    return this.event.getWeight() / totalWeight;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\LuckyEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */